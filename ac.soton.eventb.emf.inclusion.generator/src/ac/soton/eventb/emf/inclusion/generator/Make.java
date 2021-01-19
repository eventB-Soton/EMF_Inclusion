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
package ac.soton.eventb.emf.inclusion.generator;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eventb.emf.core.AbstractExtension;
import org.eventb.emf.core.CoreFactory;
import org.eventb.emf.core.Project;
import org.eventb.emf.core.context.Axiom;
import org.eventb.emf.core.context.CarrierSet;
import org.eventb.emf.core.context.Constant;
import org.eventb.emf.core.context.Context;
import org.eventb.emf.core.context.ContextFactory;
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Convergence;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Guard;
import org.eventb.emf.core.machine.Invariant;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.MachineFactory;
import org.eventb.emf.core.machine.Parameter;
import org.eventb.emf.core.machine.Variable;
import org.eventb.emf.core.machine.Witness;

import ac.soton.emf.translator.TranslationDescriptor;


/**
 * @author cfsnook
 * This is a collection of static convenience methods for making objects that are needed in translator Rules
 * 
 */

public class Make {
	
	public static String generatedById(EObject sourceElement){
		if (sourceElement instanceof AbstractExtension){
			return ((AbstractExtension)sourceElement).getExtensionId();
		}else{
			return sourceElement.toString();
		}
	}
	
	public static TranslationDescriptor descriptor(EObject parent, EStructuralFeature feature, Object value, int priority){
		return new TranslationDescriptor(parent,feature,value,Integer.valueOf(priority));
	}
	
	public static TranslationDescriptor descriptor(EObject parent, EStructuralFeature feature, Object value , int priority, Boolean remove) {
		return new TranslationDescriptor(parent, feature,value,Integer.valueOf(priority), remove);
	}
	
	
	 public static Variable variable(String name, String comment) {
		Variable v =  MachineFactory.eINSTANCE.createVariable();
	    v.setName(name);
	    v.setLocalGenerated(true);
	    v.setComment(comment);
	    return v;  
	 }
	  
	public static Invariant invariant(String name, String predicate, String comment) {
		return invariant(name, false, predicate, comment);
	}
		
	public static Invariant invariant(String name, boolean theorem, String predicate,String comment) {
		Invariant i =  MachineFactory.eINSTANCE.createInvariant();
	    i.setName(name);
	    i.setTheorem(theorem);
	    i.setPredicate(predicate);
	    i.setLocalGenerated(true);
	    i.setComment(comment);
	    return i;  
	}

	public static Object event(String name) {
		return event(name, false, Convergence.ORDINARY, Collections.<String> emptyList(), "");
	}
		
	public static Object event(String name, boolean extended, Convergence convergence, List<String> refinesNames, String comment) {
		Event e =  MachineFactory.eINSTANCE.createEvent();
	    e.setName(name);
	    e.setExtended(extended);
	    e.setConvergence(convergence);
	    e.getRefinesNames().addAll(refinesNames);
	    e.setLocalGenerated(true);
	    e.setComment(comment);
	    return e;  
	}

	public static Object parameter(String name){
		return parameter(name,"");
	}
	
	public static Object parameter(String name, String comment) {
		Parameter p =  MachineFactory.eINSTANCE.createParameter();
	    p.setName(name);
	    p.setLocalGenerated(true);
	    p.setComment(comment);
	    return p;  
	}
	
	public static Object guard(String name, String predicate){
		return guard(name, false, predicate, "");
	}
	
	public static Object guard(String name, boolean theorem, String predicate, String comment) {
		Guard g =  MachineFactory.eINSTANCE.createGuard();
	    g.setName(name);
	    g.setTheorem(theorem);
	    g.setPredicate(predicate);
	    g.setLocalGenerated(true);
	    g.setComment(comment);
	    return g;  
	}
	
	public static Object action(String name, String expression){
		return action(name, expression, "");
	}
	
	public static Object action(String name, String expression, String comment) {
		Action a =  MachineFactory.eINSTANCE.createAction();
	    a.setName(name);
	    a.setAction(expression);
	    a.setLocalGenerated(true);
	    a.setComment(comment);
	    return a;  
	}

	public static Object context(String name, String comment) {
		Context ctx =  ContextFactory.eINSTANCE.createContext();
	    ctx.setName(name);
	    ctx.setLocalGenerated(true);
	    ctx.setComment(comment);
	    return ctx;
	}

	public static Object set(String name, String comment) {
		CarrierSet set =  ContextFactory.eINSTANCE.createCarrierSet();
	    set.setName(name);
	    set.setLocalGenerated(true);
	    set.setComment(comment);
	    return set;
	}

	public static Object constant(String name, String comment) {
		Constant constant =  ContextFactory.eINSTANCE.createConstant();
	    constant.setName(name);
	    constant.setLocalGenerated(true);
	    constant.setComment(comment);
	    return constant;
	}

	public static Axiom axiom(String name, String predicate,String comment) {
		return axiom(name,false, predicate, comment);
	}
	
	public static Axiom axiom(String name, boolean theorem, String predicate,String comment) {
		Axiom axm =  ContextFactory.eINSTANCE.createAxiom();
	    axm.setName(name);
	    axm.setTheorem(theorem);
	    axm.setPredicate(predicate);
	    axm.setLocalGenerated(true);
	    axm.setComment(comment);
	    return axm;  
	}

	@Deprecated
	public static Object witness(String name, String predicate) {
		return witness(name,predicate,"");
	}
	
	public static Object witness(String name, String predicate, String comment) {
		Witness g =  MachineFactory.eINSTANCE.createWitness();
	    g.setName(name);
	    g.setPredicate(predicate);
	    g.setLocalGenerated(true);
	    g.setComment("");
	    return g;
	}

	public static Machine machine(String name, String comment) {
		Machine mch =  MachineFactory.eINSTANCE.createMachine();
	    mch.setName(name);
	    mch.setLocalGenerated(true);
	    mch.setComment(comment);
	    return mch;
	}

	/**
	 * @param projectName
	 * @param object
	 * @return
	 */
	public static Project project(String projectName, String comment) {
		Project prj =  CoreFactory.eINSTANCE.createProject();
		prj.setName(projectName);
		prj.setLocalGenerated(true);
		prj.setComment(comment);
	    return prj;

	}

//	public static Statemachine statemachine(String name, TranslationKind tkind, String comment) {
//		Statemachine statemachine = StatemachinesFactory.eINSTANCE.createStatemachine();
//		statemachine.setName(name);
//		statemachine.setTranslation(tkind);
//		statemachine.setComment(comment);
//		return statemachine;
//	}
//
//	public static Initial initialState(String name) {
//		Initial initial = StatemachinesFactory.eINSTANCE.createInitial();
//		initial.setName(name);
//		return initial;
//	}
//	
//	public static Final finalState(String name) {
//		Final finals = StatemachinesFactory.eINSTANCE.createFinal();
//		finals.setName(name);
//		return finals;
//	}
//
//	public static State state(String id, String comment) {
//		State state = StatemachinesFactory.eINSTANCE.createState();
//		state.setName(id);
//		state.setComment(comment);
//		return state;
//	}
//
//	public static Transition transition(AbstractNode source, AbstractNode target, String comment) {
//		Transition transition = StatemachinesFactory.eINSTANCE.createTransition();
//		transition.setSource(source);
//		transition.setTarget(target);
//		transition.setComment(comment);
//		return transition;
//	}


	
}
