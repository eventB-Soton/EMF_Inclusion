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
 *
 * $Id$
 *******************************************************************************/
package ac.soton.eventb.emf.inclusion.impl;

import ac.soton.eventb.emf.inclusion.InclusionPackage;
import ac.soton.eventb.emf.inclusion.MachineInclusion;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eventb.emf.core.impl.AbstractExtensionImpl;

import org.eventb.emf.core.machine.Machine;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Machine Inclusion</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ac.soton.eventb.emf.inclusion.impl.MachineInclusionImpl#getAbstractMachine <em>Abstract Machine</em>}</li>
 *   <li>{@link ac.soton.eventb.emf.inclusion.impl.MachineInclusionImpl#getConcreteMachine <em>Concrete Machine</em>}</li>
 *   <li>{@link ac.soton.eventb.emf.inclusion.impl.MachineInclusionImpl#getPrefixes <em>Prefixes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MachineInclusionImpl extends AbstractExtensionImpl implements MachineInclusion {
	

	/**
	 * The default value of the '{@link #getExtensionId() <em>Extension Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensionId()
	 * @generated CUSTOM
	 * @ordered
	 */
	protected static final String EXTENSION_ID_EDEFAULT = InclusionPackage.INCLUSION_EXTENSION_ID+".MachineInclusion";

	/**
	 * The cached value of the '{@link #getExtensionId() <em>Extension Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensionId()
	 * @generated CUSTOM
	 * @ordered
	 */
	protected String extensionId = EXTENSION_ID_EDEFAULT+"."+EcoreUtil.generateUUID();
	
	
	/**
	 * The cached value of the '{@link #getAbstractMachine() <em>Abstract Machine</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstractMachine()
	 * @generated
	 * @ordered
	 */
	protected Machine abstractMachine;

	/**
	 * The cached value of the '{@link #getConcreteMachine() <em>Concrete Machine</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcreteMachine()
	 * @generated
	 * @ordered
	 */
	protected Machine concreteMachine;

	/**
	 * The cached value of the '{@link #getPrefixes() <em>Prefixes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefixes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> prefixes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MachineInclusionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InclusionPackage.Literals.MACHINE_INCLUSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Machine getAbstractMachine() {
		if (abstractMachine != null && abstractMachine.eIsProxy()) {
			InternalEObject oldAbstractMachine = (InternalEObject)abstractMachine;
			abstractMachine = (Machine)eResolveProxy(oldAbstractMachine);
			if (abstractMachine != oldAbstractMachine) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InclusionPackage.MACHINE_INCLUSION__ABSTRACT_MACHINE, oldAbstractMachine, abstractMachine));
			}
		}
		return abstractMachine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Machine basicGetAbstractMachine() {
		return abstractMachine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstractMachine(Machine newAbstractMachine) {
		Machine oldAbstractMachine = abstractMachine;
		abstractMachine = newAbstractMachine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InclusionPackage.MACHINE_INCLUSION__ABSTRACT_MACHINE, oldAbstractMachine, abstractMachine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Machine getConcreteMachine() {
		if (concreteMachine != null && concreteMachine.eIsProxy()) {
			InternalEObject oldConcreteMachine = (InternalEObject)concreteMachine;
			concreteMachine = (Machine)eResolveProxy(oldConcreteMachine);
			if (concreteMachine != oldConcreteMachine) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InclusionPackage.MACHINE_INCLUSION__CONCRETE_MACHINE, oldConcreteMachine, concreteMachine));
			}
		}
		return concreteMachine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Machine basicGetConcreteMachine() {
		return concreteMachine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConcreteMachine(Machine newConcreteMachine) {
		Machine oldConcreteMachine = concreteMachine;
		concreteMachine = newConcreteMachine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InclusionPackage.MACHINE_INCLUSION__CONCRETE_MACHINE, oldConcreteMachine, concreteMachine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getPrefixes() {
		if (prefixes == null) {
			prefixes = new EDataTypeUniqueEList<String>(String.class, this, InclusionPackage.MACHINE_INCLUSION__PREFIXES);
		}
		return prefixes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case InclusionPackage.MACHINE_INCLUSION__ABSTRACT_MACHINE:
				if (resolve) return getAbstractMachine();
				return basicGetAbstractMachine();
			case InclusionPackage.MACHINE_INCLUSION__CONCRETE_MACHINE:
				if (resolve) return getConcreteMachine();
				return basicGetConcreteMachine();
			case InclusionPackage.MACHINE_INCLUSION__PREFIXES:
				return getPrefixes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case InclusionPackage.MACHINE_INCLUSION__ABSTRACT_MACHINE:
				setAbstractMachine((Machine)newValue);
				return;
			case InclusionPackage.MACHINE_INCLUSION__CONCRETE_MACHINE:
				setConcreteMachine((Machine)newValue);
				return;
			case InclusionPackage.MACHINE_INCLUSION__PREFIXES:
				getPrefixes().clear();
				getPrefixes().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case InclusionPackage.MACHINE_INCLUSION__ABSTRACT_MACHINE:
				setAbstractMachine((Machine)null);
				return;
			case InclusionPackage.MACHINE_INCLUSION__CONCRETE_MACHINE:
				setConcreteMachine((Machine)null);
				return;
			case InclusionPackage.MACHINE_INCLUSION__PREFIXES:
				getPrefixes().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case InclusionPackage.MACHINE_INCLUSION__ABSTRACT_MACHINE:
				return abstractMachine != null;
			case InclusionPackage.MACHINE_INCLUSION__CONCRETE_MACHINE:
				return concreteMachine != null;
			case InclusionPackage.MACHINE_INCLUSION__PREFIXES:
				return prefixes != null && !prefixes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (prefixes: ");
		result.append(prefixes);
		result.append(')');
		return result.toString();
	}

} //MachineInclusionImpl
