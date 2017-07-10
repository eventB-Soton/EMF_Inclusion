/*******************************************************************************
 * Copyright (c) 2017 University of Southampton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     University of Southampton - initial API and implementation
 *******************************************************************************/
package ac.soton.eventb.emf.inclusion.generator.rules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.common.util.EList;
import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.AbstractRule;
import ac.soton.emf.translator.configuration.IRule;
import org.eventb.emf.core.AbstractExtension;
import org.eventb.emf.core.CorePackage;
import ac.soton.eventb.emf.inclusion.generator.Make;
import ac.soton.eventb.emf.inclusion.MachineInclusion;
import ac.soton.eventb.emf.inclusion.EventSynchronisation;
import org.eventb.emf.core.Project;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.persistence.ProjectResource;
import org.eclipse.emf.common.util.URI;
import org.eventb.emf.core.machine.Variable;
import org.eventb.emf.core.machine.Invariant;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Parameter;
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Guard;
import org.eventb.emf.core.machine.Witness;

/**
 * Machine Inclusion Translation Rule
 * <p>
 * 
 * </p>
 * 
 * @author Dana
 * @version
 * @see
 * @since
 */
public class MachineInclusionRule extends AbstractRule implements IRule{
	protected static final EReference components = CorePackage.Literals.PROJECT__COMPONENTS;
	@Override
	public boolean enabled(final EObject sourceElement) throws Exception  {
		if (sourceElement.eContainer() instanceof Machine)
			return true;
		else
			return false;
	}

	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> translatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
	    Machine sourceMachine = (Machine)sourceElement.eContainer(); 
        EList<AbstractExtension> extensions = sourceMachine.getExtensions();
        List<MachineInclusion> includedMachines = new ArrayList<MachineInclusion>();
		
		for(AbstractExtension ext: extensions){
			if (ext instanceof MachineInclusion)
				includedMachines.add((MachineInclusion)ext);
		}
		
		Project proj = findProject(sourceElement);
		
		Machine abstractFlattenedMachine = Make.machine(sourceMachine.getName(), "");//+"_flattened" //flattened suffix for coda
		
		//Clone the included machine invariants and variables and apply prefixing 
		// Synchronising the events is done as part of cloning the sourceMachine elements
		
		for (MachineInclusion mch: includedMachines){
			Machine abstractMch = mch.getAbstractMachine();
			if(abstractMch != null && !mch.getPrefixes().isEmpty()){
				for(String pref: mch.getPrefixes()){
					abstractFlattenedMachine.getVariables().addAll(copyVariables(abstractMch, pref));
					abstractFlattenedMachine.getInvariants().addAll(copyInvariants(abstractMch, pref));
				}
			}
			else if(abstractMch != null && mch.getPrefixes().isEmpty()){
				abstractFlattenedMachine.getVariables().addAll(copyVariables(abstractMch, ""));
				abstractFlattenedMachine.getInvariants().addAll(copyInvariants(abstractMch, ""));
			}
		}		
		
		//Clone the source machine
		abstractFlattenedMachine.getRefinesNames().addAll(copyMachineRefinesNames(sourceMachine));
		abstractFlattenedMachine.getSeesNames().addAll(copyMachineSeesNames(sourceMachine));
		abstractFlattenedMachine.getVariables().addAll(copyVariables(sourceMachine, ""));
		abstractFlattenedMachine.getInvariants().addAll(copyInvariants(sourceMachine, ""));
		abstractFlattenedMachine.getEvents().addAll(copyEvents(sourceMachine));
		
	    ret.add(Make.descriptor(proj, components, abstractFlattenedMachine, 1));
		
		return ret;
	}
	
	@Override
	public boolean dependenciesOK(EObject sourceElement, final List<TranslationDescriptor> translatedElements) throws Exception  {
		return true;
	}
	
	@Override
	public boolean fireLate() {
		return false;
	}
	

	private Project findProject(EObject sourceElement) throws IOException {
		
		URI eventBelementUri = sourceElement.eResource().getURI();
		URI projectUri = eventBelementUri.trimFragment().trimSegments(1);
		ProjectResource projectResource = new ProjectResource();
		projectResource.setURI(projectUri);
		projectResource.load(null);
		for (EObject eObject : projectResource.getContents()){
			if (eObject instanceof Project){
				return (Project)eObject;
			}
		}
		return null;
	}
	//****************************************************************
    // Private Methods to clone the machine related elements
    //****************************************************************
		
	private List<String> copyMachineRefinesNames(Machine sourceMachine){
		ArrayList <String> refineList= new ArrayList<String>();
		//for(String str: sourceMachine.getRefinesNames())
			//refineList.add(str);
		refineList.addAll(sourceMachine.getRefinesNames());
		return refineList;
	}
	private List<String> copyMachineSeesNames(Machine sourceMachine){
		ArrayList <String> seesList= new ArrayList<String>();
		seesList.addAll(sourceMachine.getSeesNames());
		return seesList;
	}

	private List<Variable> copyVariables(Machine mch, String prefix){
		ArrayList <Variable> varList = new ArrayList<Variable>();
		
		for(Variable var : mch.getVariables()){
			Variable newVar;
			if(prefix != "")
				newVar = Make.variable(prefix + "_" + var.getName(), var.getComment());
			else
				newVar = Make.variable(var.getName(), var.getComment());
			varList.add(newVar);
		}		
		return varList;		
	}
	
	private List<Invariant> copyInvariants(Machine mch, String prefix){
		ArrayList <Invariant> invList = new ArrayList<Invariant>();
		
		for(Invariant inv : mch.getInvariants()){
			Invariant newInv;
			if(prefix != ""){
				
				String pred = renameInvariant(mch, inv.getPredicate(), prefix);
				newInv= Make.invariant(prefix + "_" + inv.getName(), inv.isTheorem(),pred,inv.getComment());
			}
				
			else
				newInv = Make.invariant(inv.getName(), inv.isTheorem(), inv.getPredicate(),inv.getComment());
			
			invList.add(newInv);
		}		
		return invList;		
	}
	private String renameInvariant(Machine sourceMachine, String predicate, String prefix) {
		String newPredicate = predicate;
		String[] tokens = predicate.split("\\W");
		//Remove repeated tokens
		ArrayList<String> newTokens = removeReptition(tokens);
	
		for (String tok : newTokens){
			for (Variable v : sourceMachine.getVariables()){
				if (tok.equals(v.getName()))
					newPredicate = newPredicate.replaceAll("\\b" + tok + "\\b", prefix + "_" + tok);				
			}
		}
		return newPredicate;
	}
	
	
	private List<Event> copyEvents(Machine sourceMachine){
		ArrayList <Event> evtList = new ArrayList<Event>();
		for (Event evt : sourceMachine.getEvents()){

				Event newEvt = (Event) Make.event(evt.getName(),evt.isExtended(), evt.getConvergence(), evt.getRefinesNames(), evt.getComment());
								
            	// Check for synchronised events
				if(!evt.getExtensions().isEmpty()){
					for(AbstractExtension ext: evt.getExtensions()){
						if(ext instanceof EventSynchronisation){
							EventSynchronisation eventSynch = (EventSynchronisation) ext;
							Event synchEvt = eventSynch.getSynchronisedEvent();
							String pref = eventSynch.getPrefix();
							Machine mch = null;
							if (synchEvt.eContainer() instanceof Machine)
							 mch = (Machine) synchEvt.eContainer();
							newEvt.getParameters().addAll(copyParameters(synchEvt , pref));
			            	newEvt.getActions().addAll(copyActions(mch, synchEvt, pref));
			            	newEvt.getGuards().addAll(copyGuards(mch, synchEvt, pref));
			            	newEvt.getWitnesses().addAll(copyWitnesses(mch, synchEvt, pref));
						}
					}
					
					//get source event data
	            	newEvt.getParameters().addAll(copyParameters(evt, ""));
	            	newEvt.getActions().addAll(copyActions(sourceMachine, evt, ""));
	            	newEvt.getGuards().addAll(copyGuards(sourceMachine, evt, ""));
	            	newEvt.getWitnesses().addAll(copyWitnesses(sourceMachine, evt, ""));
								
				}
				evtList.add(newEvt);
			}
					
		return evtList;
	}
	//****************************************************************
    // Private Methods to clone the event related elements
    //****************************************************************
	
	// Clone Parameter list of event and rename the parameters if required
	private ArrayList<Parameter> copyParameters(Event evt, String prefix){
		ArrayList <Parameter> parsList = new ArrayList<Parameter>();
		for(Parameter par: evt.getParameters())
		{
			String name = "";
			if(prefix != "")
				name = prefix + "_" + par.getName();
			else
				name = par.getName();
			
			Parameter newPar = (Parameter) Make.parameter(name, par.getComment());
			parsList.add(newPar);
		}		
		return parsList;
	}
	
	
	// Clone Actionlist of event and rename the actions if required
	private ArrayList<Action> copyActions(Machine sourceMachine, Event evt, String prefix){
		ArrayList <Action> actsList = new ArrayList<Action>();
		for(Action act: evt.getActions()){
			String name = "";
			String exp = "";
			if(prefix != ""){
				name = prefix + "_" + act.getName();
				exp =  renameEvent(sourceMachine, evt, act.getAction(), prefix);
			}
			else{
				name = act.getName();
				exp =  act.getAction();
			}
			 Action newAct = (Action) Make.action(name, exp, act.getComment());
			
			actsList.add(newAct);	
		}
		return actsList;
	}
	
	
	//Clone guards
	private ArrayList<Guard> copyGuards(Machine sourceMachine, Event evt, String prefix){
		ArrayList <Guard> grdList = new ArrayList<Guard>();
		for(Guard grd: evt.getGuards()){
			String name = "";
			String predicate = "";
			if(prefix != ""){
				name = prefix + "_" + grd.getName();
				predicate =  renameEvent(sourceMachine, evt, grd.getPredicate(), prefix);
			}
			else{
				name = grd.getName();
				predicate =  grd.getPredicate();
			}
			 Guard newGrd = (Guard) Make.guard(name, grd.isTheorem(), predicate, grd.getComment());
			
			grdList.add(newGrd);	
		}
		return grdList;
	}
	
	//Clone witnesses
	private ArrayList<Witness> copyWitnesses(Machine sourceMachine, Event evt, String prefix){
		ArrayList <Witness> witList = new ArrayList<Witness>();
		for(Witness wit: evt.getWitnesses()){
			String name = "";
			String predicate = "";
			if(prefix != ""){
				name = prefix + "_" + wit.getName();
				predicate =  renameEvent(sourceMachine, evt, wit.getPredicate(), prefix);
			}
			else{
				name = wit.getName();
				predicate =  wit.getPredicate();
			}
			
			 Witness newWit =  (Witness) Make.witness(name, predicate, wit.getComment());
			
			witList.add(newWit);	
		}
		return witList;
	}
   
	// This method is used to help add prefixing for events guards and action
	// This is different to invariant prefixing because it also checks for parameters
	
	private String renameEvent(Machine sourceMachine, Event event, String predicate, String prefix) {
		String newPredicate = predicate;
		String[] tokens = predicate.split("\\W");
		//Remove repeated tokens
		ArrayList<String> newTokens = removeReptition(tokens);
		
		for (String tok : newTokens){
			for (Variable v : sourceMachine.getVariables()){
				if (tok.equals(v.getName()))
					newPredicate = newPredicate.replaceAll("\\b" + tok + "\\b", prefix + "_" + tok);	
				else{
					for (Parameter p : event.getParameters()){
						if (tok.equals(p.getName()))
							newPredicate = newPredicate.replaceAll("\\b" + tok + "\\b", prefix + "_" + tok);	
					}
				}
			}
		}
		return newPredicate;
	}
	
	
	private ArrayList<String> removeReptition(String[] tokens){
		ArrayList<String> newTokens = new ArrayList<String>();
		for(String tok: tokens){
			if(!newTokens.contains(tok))
				newTokens.add(tok);
		}
		return newTokens;
	}
}
