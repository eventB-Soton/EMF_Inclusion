/*******************************************************************************
 * Copyright (c) 2017, 2021 University of Southampton.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     University of Southampton - initial API and implementation
 *******************************************************************************/
package ac.soton.eventb.emf.inclusion.generator.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eventb.emf.core.CorePackage;
import org.eventb.emf.core.machine.Invariant;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.Variable;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.AbstractRule;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.emf.inclusion.MachineInclusion;


/**
 * <p>
 * Implementation of {@link AbstractRule} for translating machine inclusion into flattened Event-B machine
 * </p>
 * 
 * @author Dana
 * @version 0.2
 * @see TranslationDescriptor
 * @since 0.1.0
 */
public class MachineInclusionRule extends AbstractEventBGeneratorRule implements IRule {
	protected static final EReference components = CorePackage.Literals.PROJECT__COMPONENTS;
	

	@Override
	public boolean enabled(final EObject sourceElement) throws Exception  {
		if(sourceElement instanceof MachineInclusion) {
			return true;
		}
		return false;
	}

	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> translatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
	    Machine sourceMachine = (Machine)sourceElement.eContainer(); 
		
		MachineInclusion inclusion = (MachineInclusion) sourceElement;
		Machine abstractMch = inclusion.getAbstractMachine();

		// Do nothing if there are no abstract machine
		if (abstractMch == null)
			return ret;

		if(!inclusion.getPrefixes().isEmpty()) {
			// If there are prefixes then generate variables and invariants using the prefixes.
			for(String pref: inclusion.getPrefixes()){
				ret.addAll(0,copyVariables(inclusion, abstractMch, sourceMachine, pref));
				ret.addAll(0,copyInvariants(inclusion, abstractMch, sourceMachine, pref));
			}
		}
		else {
			// If there are no prefixes then generate variables and invariants without the prefix.
			ret.addAll(0,copyVariables(inclusion, abstractMch, sourceMachine, ""));
			ret.addAll(0,copyInvariants(inclusion, abstractMch, sourceMachine, ""));
		}
			
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
    // Private Methods to clone the machine related elements
    //****************************************************************
		

	private List<TranslationDescriptor> copyVariables(MachineInclusion inclusion, Machine fromMch, Machine toMch, String prefix){
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		
		for(Variable var : fromMch.getVariables()){
			Variable newVar;
			if(prefix != "")
				newVar = Make.variable(prefix + "_" + var.getName(), var.getComment());
			else
				newVar = Make.variable(var.getName(), var.getComment());
			ret.add(Make.descriptor(toMch, orderedChildren, newVar, inclusion, 0, inclusion));
		}		
		return ret;		
	}
	
	private List<TranslationDescriptor> copyInvariants(MachineInclusion inclusion, Machine fromMch, Machine toMch, String prefix){
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		
		for(Invariant inv : fromMch.getInvariants()){
			Invariant newInv;
			if(prefix != ""){
				String pred = renameInvariant(fromMch, inv.getPredicate(), prefix);
				newInv= Make.invariant(prefix + "_" + inv.getName(), inv.isTheorem(),pred,inv.getComment());
			}
				
			else
				newInv = Make.invariant(inv.getName(), inv.isTheorem(), inv.getPredicate(),inv.getComment());
			
			ret.add(Make.descriptor(toMch, orderedChildren, newInv, inclusion, 0, inclusion));
		}		
		return ret;		
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
