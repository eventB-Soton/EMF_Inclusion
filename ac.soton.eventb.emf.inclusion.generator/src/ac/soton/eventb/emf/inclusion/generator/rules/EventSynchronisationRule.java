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
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Guard;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.Parameter;
import org.eventb.emf.core.machine.Variable;
import org.eventb.emf.core.machine.Witness;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.AbstractRule;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.emf.inclusion.EventSynchronisation;

/**
 * <p>
 * Implementation of {@link AbstractRule} for translating event synchronisation
 * into flattened Event-B event.
 * </p>
 * 
 * @author Dana
 * @version 0.1
 * @see TranslationDescriptor
 * @since 0.2.0
 */

public class EventSynchronisationRule extends AbstractEventBGeneratorRule implements IRule {
//	protected static final EReference events = MachinePackage.Literals.MACHINE__EVENTS;

	@Override
	public boolean enabled(final EObject sourceElement) throws Exception {
		if (sourceElement instanceof EventSynchronisation)
			return true;

		else
			return false;
	}

	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> translatedElements)
			throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		EventSynchronisation eventSynch = (EventSynchronisation) sourceElement;
		Event evt = (Event) eventSynch.eContainer();
		Event synchEvt = eventSynch.getSynchronisedEvent();
		String pref = eventSynch.getPrefix();
		if (synchEvt.eContainer() instanceof Machine) {
			Machine mch = (Machine) synchEvt.eContainer();
			ret.addAll(copyParameters(evt, synchEvt, pref));
			ret.addAll(copyActions(mch, evt, synchEvt, pref));
			ret.addAll(copyGuards(mch, evt, synchEvt, pref));
			ret.addAll(copyWitnesses(mch, evt, synchEvt, pref));
		}

		return ret;
	}

	@Override
	public boolean dependenciesOK(EObject sourceElement, final List<TranslationDescriptor> translatedElements)
			throws Exception {
		return true;
	}

	@Override
	public boolean fireLate() {
		return false;
	}

	// ****************************************************************
	// Private Methods to clone the event related elements
	// ****************************************************************

	// Clone Parameter list of event and rename the parameters if required
	private List<TranslationDescriptor> copyParameters(Event targetEvt, Event sourceEvt, String prefix) {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		for (Parameter par : sourceEvt.getParameters()) {
			String name = "";
			if (prefix != "")
				name = prefix + "_" + par.getName();
			else
				name = par.getName();

			Parameter newPar = (Parameter) Make.parameter(name, par.getComment());
			ret.add(Make.descriptor(targetEvt, orderedChildren, newPar, null, 0));
		}
		return ret;
	}

	// Clone Actionlist of event and rename the actions if required
	private List<TranslationDescriptor> copyActions(Machine sourceMachine, Event targetEvt, Event sourceEvt, String prefix) {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		for (Action act : sourceEvt.getActions()) {
			String name = "";
			String exp = "";
			if (prefix != "") {
				name = prefix + "_" + act.getName();
				exp = renameFormulae(sourceMachine, sourceEvt, act.getAction(), prefix);
			} else {
				name = act.getName();
				exp = act.getAction();
			}
			Action newAct = (Action) Make.action(name, exp, act.getComment());

			ret.add(Make.descriptor(targetEvt, orderedChildren, newAct, null, 0));
		}
		return ret;
	}

	// Clone guards
	private List<TranslationDescriptor> copyGuards(Machine sourceMachine, Event targetEvt, Event sourceEvt, String prefix) {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		for (Guard grd : sourceEvt.getGuards()) {
			String name = "";
			String predicate = "";
			if (prefix != "") {
				name = prefix + "_" + grd.getName();
				predicate = renameFormulae(sourceMachine, sourceEvt, grd.getPredicate(), prefix);
			} else {
				name = grd.getName();
				predicate = grd.getPredicate();
			}
			Guard newGrd = (Guard) Make.guard(name, grd.isTheorem(), predicate, grd.getComment());
			ret.add(Make.descriptor(targetEvt, orderedChildren, newGrd, null, 0));
		}
		return ret;
	}

	// Clone witnesses
	private List<TranslationDescriptor> copyWitnesses(Machine sourceMachine, Event targetEvt, Event sourceEvt, String prefix) {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		for (Witness wit : sourceEvt.getWitnesses()) {
			String name = "";
			String predicate = "";
			if (prefix != "") {
				name = prefix + "_" + wit.getName();
				predicate = renameFormulae(sourceMachine, sourceEvt, wit.getPredicate(), prefix);
			} else {
				name = wit.getName();
				predicate = wit.getPredicate();
			}

			Witness newWit = (Witness) Make.witness(name, predicate, wit.getComment());
			ret.add(Make.descriptor(targetEvt, orderedChildren, newWit, null, 0));
		}
		return ret;
	}

	// This method is used to help add prefixing for events guards and action
	// This is different to invariant prefixing because it also checks for
	// parameters

	private String renameFormulae(Machine sourceMachine, Event event, String formulae, String prefix) {
		String newPredicate = formulae;
		String[] tokens = formulae.split("\\W");
		// Remove repeated tokens
		ArrayList<String> newTokens = removeReptition(tokens);

		for (String tok : newTokens) {
			for (Variable v : sourceMachine.getVariables()) {
				if (tok.equals(v.getName()))
					newPredicate = newPredicate.replaceAll("\\b" + tok + "\\b", prefix + "_" + tok);
			}
			for (Parameter p : event.getParameters()) {
				if (tok.equals(p.getName())) {
					newPredicate = newPredicate.replaceAll("\\b" + tok + "\\b", prefix + "_" + tok);
				}
			}
		}
		return newPredicate;
	}

	// If a utilities class is defined move this method there
	private ArrayList<String> removeReptition(String[] tokens) {
		ArrayList<String> newTokens = new ArrayList<String>();
		for (String tok : tokens) {
			if (!newTokens.contains(tok))
				newTokens.add(tok);
		}
		return newTokens;
	}
}
