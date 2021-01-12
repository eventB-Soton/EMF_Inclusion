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

import ac.soton.eventb.emf.inclusion.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class InclusionFactoryImpl extends EFactoryImpl implements InclusionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static InclusionFactory init() {
		try {
			InclusionFactory theInclusionFactory = (InclusionFactory)EPackage.Registry.INSTANCE.getEFactory("http://soton.ac.uk/models/eventb/inclusion/2017"); 
			if (theInclusionFactory != null) {
				return theInclusionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new InclusionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InclusionFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case InclusionPackage.MACHINE_INCLUSION: return createMachineInclusion();
			case InclusionPackage.EVENT_SYNCHRONISATION: return createEventSynchronisation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MachineInclusion createMachineInclusion() {
		MachineInclusionImpl machineInclusion = new MachineInclusionImpl();
		return machineInclusion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventSynchronisation createEventSynchronisation() {
		EventSynchronisationImpl eventSynchronisation = new EventSynchronisationImpl();
		return eventSynchronisation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InclusionPackage getInclusionPackage() {
		return (InclusionPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static InclusionPackage getPackage() {
		return InclusionPackage.eINSTANCE;
	}

} //InclusionFactoryImpl
