/**
 * Copyright (c) 2017
 * University of Southampton.
 * All rights reserved. This program and the accompanying materials  are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies this 
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 *
 * $Id$
 */
package ac.soton.eventb.emf.inclusion.tests;

import org.junit.Test;

import ac.soton.eventb.emf.inclusion.InclusionFactory;
import ac.soton.eventb.emf.inclusion.MachineInclusion;
import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Machine Inclusion</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class MachineInclusionTest extends TestCase {

	/**
	 * The fixture for this Machine Inclusion test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MachineInclusion fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MachineInclusionTest.class);
	}

	/**
	 * Constructs a new Machine Inclusion test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MachineInclusionTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Machine Inclusion test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(MachineInclusion fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Machine Inclusion test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MachineInclusion getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(InclusionFactory.eINSTANCE.createMachineInclusion());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * @since 0.1
	 */
	@Test
	public void testMachineInclusion() {
		// Ignore at the moment
	}
} //MachineInclusionTest
