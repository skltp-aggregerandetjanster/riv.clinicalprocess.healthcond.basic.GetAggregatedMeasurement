/**
 * Copyright (c) 2014 Inera AB, <http://inera.se/>
 *
 * This file is part of SKLTP.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RequestListFactoryTest {
	
	@Test
	public void isPartOf(){
		assertTrue(new RequestListFactoryImpl().isPartOf("UNIT2", "UNIT2"));
		assertTrue(new RequestListFactoryImpl().isPartOf(null, "UNIT2"));
		assertTrue(new RequestListFactoryImpl().isPartOf("", "UNIT2"));
	}
	
	@Test
	public void isNotPartOf(){
		assertFalse(new RequestListFactoryImpl().isPartOf("UNIT2", "UNIT3"));
		assertFalse(new RequestListFactoryImpl().isPartOf("UNIT2", null));
	}
}
