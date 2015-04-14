
-- =========================================================================== 
-- Different consumers in different environments
-- SKLTP-BOX consumer:		client
-- SKLTP Verify consumer:	HSASERVICES-100M
-- ===========================================================================

SET @consumerHsaId = 'client';

SET @serviceContractNamespace = 'urn:riv:clinicalprocess:healthcond:basic:GetMeasurement:1:rivtabp21';
SET @popularDomainName = 'Hantera hälsorelaterade tillstånd, basuppgifter';
SET @pathToServices = 'GetMeasurement/1/rivtabp21';

-- =========================================================================== 
-- Setup routing in TAK
-- ===========================================================================

-- Sätt upp logiska adressater
INSERT INTO LogiskAdressat (beskrivning, hsaId, version) VALUES('Används vid tester av agg tjänst.', 'HSA-ID-1', 0);
INSERT INTO LogiskAdressat (beskrivning, hsaId, version) VALUES('Används vid tester av agg tjänst.', 'HSA-ID-2', 0);
INSERT INTO LogiskAdressat (beskrivning, hsaId, version) VALUES('Används vid tester av agg tjänst.', 'HSA-ID-3', 0);
INSERT INTO LogiskAdressat (beskrivning, hsaId, version) VALUES('Används vid tester av agg tjänst.', 'HSA-ID-4', 0);
INSERT INTO LogiskAdressat (beskrivning, hsaId, version) VALUES('Används vid tester av agg tjänst.', 'HSA-ID-5', 0);
INSERT INTO LogiskAdressat (beskrivning, hsaId, version) VALUES('Används vid tester av agg tjänst.', 'HSA-ID-6', 0);
INSERT INTO LogiskAdressat (beskrivning, hsaId, version) VALUES('Används vid tester av agg tjänst.', 'HSA-ID-7', 0);

-- Sätt upp tjänstekomponent, teststubbe
INSERT INTO Tjanstekomponent (adress, beskrivning, hsaId, version) VALUES(CONCAT('http://localhost:20007/producer/teststub/',@pathToServices), 'Producent vid tester av Aggregerandetjänst.', CONCAT('HSA-ID-TESTSTUBBE-',@popularDomainName), 0);

-- Sätt upp logiska adresser
INSERT INTO LogiskAdress (fromTidpunkt, tomTidpunkt, version, logiskAdressat_id, rivVersion_id, tjanstekontrakt_id, tjansteproducent_id)
select '2013-10-07', '2113-10-07', 0, adressat.id, riv.id, kontrakt.id, producent.id from LogiskAdressat as adressat, RivVersion as riv,
Tjanstekontrakt as kontrakt, Tjanstekomponent as producent
where adressat.hsaId = 'HSA-ID-1'
and riv.namn = 'RIVTABP21'
and kontrakt.namnrymd = @serviceContractNamespace
and producent.hsaId = CONCAT('HSA-ID-TESTSTUBBE-',@popularDomainName);

INSERT INTO LogiskAdress (fromTidpunkt, tomTidpunkt, version, logiskAdressat_id, rivVersion_id, tjanstekontrakt_id, tjansteproducent_id)
select '2013-10-07', '2113-10-07', 0, adressat.id, riv.id, kontrakt.id, producent.id from LogiskAdressat as adressat, RivVersion as riv,
Tjanstekontrakt as kontrakt, Tjanstekomponent as producent
where adressat.hsaId = 'HSA-ID-2'
and riv.namn = 'RIVTABP21'
and kontrakt.namnrymd = @serviceContractNamespace
and producent.hsaId = CONCAT('HSA-ID-TESTSTUBBE-',@popularDomainName);

INSERT INTO LogiskAdress (fromTidpunkt, tomTidpunkt, version, logiskAdressat_id, rivVersion_id, tjanstekontrakt_id, tjansteproducent_id)
select '2013-10-07', '2113-10-07', 0, adressat.id, riv.id, kontrakt.id, producent.id from LogiskAdressat as adressat, RivVersion as riv,
Tjanstekontrakt as kontrakt, Tjanstekomponent as producent
where adressat.hsaId = 'HSA-ID-3'
and riv.namn = 'RIVTABP21'
and kontrakt.namnrymd = @serviceContractNamespace
and producent.hsaId = CONCAT('HSA-ID-TESTSTUBBE-',@popularDomainName);

INSERT INTO LogiskAdress (fromTidpunkt, tomTidpunkt, version, logiskAdressat_id, rivVersion_id, tjanstekontrakt_id, tjansteproducent_id)
select '2013-10-07', '2113-10-07', 0, adressat.id, riv.id, kontrakt.id, producent.id from LogiskAdressat as adressat, RivVersion as riv,
Tjanstekontrakt as kontrakt, Tjanstekomponent as producent
where adressat.hsaId = 'HSA-ID-4'
and riv.namn = 'RIVTABP21'
and kontrakt.namnrymd = @serviceContractNamespace
and producent.hsaId = CONCAT('HSA-ID-TESTSTUBBE-',@popularDomainName);

INSERT INTO LogiskAdress (fromTidpunkt, tomTidpunkt, version, logiskAdressat_id, rivVersion_id, tjanstekontrakt_id, tjansteproducent_id)
select '2013-10-07', '2113-10-07', 0, adressat.id, riv.id, kontrakt.id, producent.id from LogiskAdressat as adressat, RivVersion as riv,
Tjanstekontrakt as kontrakt, Tjanstekomponent as producent
where adressat.hsaId = 'HSA-ID-5'
and riv.namn = 'RIVTABP21'
and kontrakt.namnrymd = @serviceContractNamespace
and producent.hsaId = CONCAT('HSA-ID-TESTSTUBBE-',@popularDomainName);

INSERT INTO LogiskAdress (fromTidpunkt, tomTidpunkt, version, logiskAdressat_id, rivVersion_id, tjanstekontrakt_id, tjansteproducent_id)
select '2013-10-07', '2113-10-07', 0, adressat.id, riv.id, kontrakt.id, producent.id from LogiskAdressat as adressat, RivVersion as riv,
Tjanstekontrakt as kontrakt, Tjanstekomponent as producent
where adressat.hsaId = 'HSA-ID-6'
and riv.namn = 'RIVTABP21'
and kontrakt.namnrymd = @serviceContractNamespace
and producent.hsaId = CONCAT('HSA-ID-TESTSTUBBE-',@popularDomainName);

INSERT INTO LogiskAdress (fromTidpunkt, tomTidpunkt, version, logiskAdressat_id, rivVersion_id, tjanstekontrakt_id, tjansteproducent_id)
select '2013-10-07', '2113-10-07', 0, adressat.id, riv.id, kontrakt.id, producent.id from LogiskAdressat as adressat, RivVersion as riv,
Tjanstekontrakt as kontrakt, Tjanstekomponent as producent
where adressat.hsaId = 'HSA-ID-7'
and riv.namn = 'RIVTABP21'
and kontrakt.namnrymd = @serviceContractNamespace
and producent.hsaId = CONCAT('HSA-ID-TESTSTUBBE-',@popularDomainName);


-- =========================================================================== 
-- Setup authoriation in TAK
-- ===========================================================================

#INSERT INTO Tjanstekomponent (adress, beskrivning, hsaId, version) VALUES('', 'Test konsument', @consumerHsaId, 0);

INSERT INTO `Anropsbehorighet` (`fromTidpunkt`, `integrationsavtal`, `tomTidpunkt`, `version`, `logiskAdressat_id`, `tjanstekonsument_id`, `tjanstekontrakt_id`)
select '2013-01-01', 'Integrationstest Aggregerandetjänst', '2114-12-31', 0, adressat.id, konsument.id, kontrakt.id
from `LogiskAdressat` as adressat, `Tjanstekontrakt` as kontrakt, `Tjanstekomponent` as konsument
where adressat.hsaId = 'HSA-ID-1'
and kontrakt.namnrymd = @serviceContractNamespace
and konsument.hsaId = @consumerHsaId;

INSERT INTO `Anropsbehorighet` (`fromTidpunkt`, `integrationsavtal`, `tomTidpunkt`, `version`, `logiskAdressat_id`, `tjanstekonsument_id`, `tjanstekontrakt_id`)
select '2013-01-01', 'Integrationstest Aggregerandetjänst', '2114-12-31', 0, adressat.id, konsument.id, kontrakt.id
from `LogiskAdressat` as adressat, `Tjanstekontrakt` as kontrakt, `Tjanstekomponent` as konsument
where adressat.hsaId = 'HSA-ID-2'
and kontrakt.namnrymd = @serviceContractNamespace
and konsument.hsaId = @consumerHsaId;

INSERT INTO `Anropsbehorighet` (`fromTidpunkt`, `integrationsavtal`, `tomTidpunkt`, `version`, `logiskAdressat_id`, `tjanstekonsument_id`, `tjanstekontrakt_id`)
select '2013-01-01', 'Integrationstest Aggregerandetjänst', '2114-12-31', 0, adressat.id, konsument.id, kontrakt.id
from `LogiskAdressat` as adressat, `Tjanstekontrakt` as kontrakt, `Tjanstekomponent` as konsument
where adressat.hsaId = 'HSA-ID-3'
and kontrakt.namnrymd = @serviceContractNamespace
and konsument.hsaId = @consumerHsaId;

INSERT INTO `Anropsbehorighet` (`fromTidpunkt`, `integrationsavtal`, `tomTidpunkt`, `version`, `logiskAdressat_id`, `tjanstekonsument_id`, `tjanstekontrakt_id`)
select '2013-01-01', 'Integrationstest Aggregerandetjänst', '2114-12-31', 0, adressat.id, konsument.id, kontrakt.id
from `LogiskAdressat` as adressat, `Tjanstekontrakt` as kontrakt, `Tjanstekomponent` as konsument
where adressat.hsaId = 'HSA-ID-4'
and kontrakt.namnrymd = @serviceContractNamespace
and konsument.hsaId = @consumerHsaId;

INSERT INTO `Anropsbehorighet` (`fromTidpunkt`, `integrationsavtal`, `tomTidpunkt`, `version`, `logiskAdressat_id`, `tjanstekonsument_id`, `tjanstekontrakt_id`)
select '2013-01-01', 'Integrationstest Aggregerandetjänst', '2114-12-31', 0, adressat.id, konsument.id, kontrakt.id
from `LogiskAdressat` as adressat, `Tjanstekontrakt` as kontrakt, `Tjanstekomponent` as konsument
where adressat.hsaId = 'HSA-ID-5'
and kontrakt.namnrymd = @serviceContractNamespace
and konsument.hsaId = @consumerHsaId;

INSERT INTO `Anropsbehorighet` (`fromTidpunkt`, `integrationsavtal`, `tomTidpunkt`, `version`, `logiskAdressat_id`, `tjanstekonsument_id`, `tjanstekontrakt_id`)
select '2013-01-01', 'Integrationstest Aggregerandetjänst', '2114-12-31', 0, adressat.id, konsument.id, kontrakt.id
from `LogiskAdressat` as adressat, `Tjanstekontrakt` as kontrakt, `Tjanstekomponent` as konsument
where adressat.hsaId = 'HSA-ID-6'
and kontrakt.namnrymd = @serviceContractNamespace
and konsument.hsaId = @consumerHsaId;

# Ingen samverkan med producent HSA-ID-7

