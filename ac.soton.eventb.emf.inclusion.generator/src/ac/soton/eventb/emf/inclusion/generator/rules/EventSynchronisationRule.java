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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Guard;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.MachinePackage;
import org.eventb.emf.core.machine.Parameter;
import org.eventb.emf.core.machine.Variable;
import org.eventb.emf.core.machine.Witness;
import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.AbstractRule;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.eventb.emf.inclusion.EventSynchronisation;
import ac.soton.eventb.emf.inclusion.generator.Make;

/**
 * <p>
 * Implementation of {@link AbstractRule} for translating event synchronisation into flattened Event-B event.
 * </p>
 * 
 * @author Dana
 * @version 0.1
 * @see TranslationDescriptor
 * @since 0.2.0
 */

public class EventSynchronisationRule extends AbstractRule implements IRule{
		protected static final EReference events = MachinePackage.Literals.MACHINE__EVENTS;
		
		@Override
		public boolean enabled(final EObject sourceElement) throws Exception  {
			if(sourceElement instanceof EventSynchronisation)
				return true;
			
				
			else
				return false;
		}

		@Override
		public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> translatedElements) throws Exception {
			List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
			EventSynchronisation eventSynch = (EventSynchronisation) sourceElement;
			Machine mch = null;
			if (sourceElement.eContainer().eContainer() instanceof Machine)
				mch = (Machine)sourceElement.eContainer().eContainer();
			Event evt = copyEvents(eventSynch);
		    ret.add(Make.descriptor(mch, events, evt, 2));
			
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
		

		//****************************************************************
	    // Private Methods for event synchronisation
	    //****************************************************************	
		private Event copyEvents(EventSynchronisation eventSynch){
		
			Event evt = (Event) eventSynch.eContainer();
			Event synchEvt = eventSynch.getSynchronisedEvent();
			String pref = eventSynch.getPrefix();
			Machine mch = null;
			if (synchEvt.eContainer() instanceof Machine){
				mch = (Machine) synchEvt.eContainer();
				evt.getParameters().addAll(0,copyParameters(synchEvt , pref));
				evt.getActions().addAll(0,copyActions(mch, synchEvt, pref));
				evt.getGuards().addAll(0,copyGuards(mch, synchEvt, pref));
				evt.getWitnesses().addAll(0,copyWitnesses(mch, synchEvt, pref));
			}
							
			return evt;
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
		
		// If a utilities class is defined move this method there
		private ArrayList<String> removeReptition(String[] tokens){
			ArrayList<String> newTokens = new ArrayList<String>();
			for(String tok: tokens){
				if(!newTokens.contains(tok))
					newTokens.add(tok);
			}
			return newTokens;
		}
	}

