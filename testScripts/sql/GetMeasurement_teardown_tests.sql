-- Detta script tar bort all data  från TAK som behövdes för testerna

SET @popularDomainName = 'Hantera hälsorelaterade tillstånd, basuppgifter';

-- Ta bort all anropsbehörigheter

DELETE FROM `Anropsbehorighet` WHERE integrationsavtal = 'Integrationstest Aggregerandetjänst';

-- Ta bort logiska adresser
DELETE FROM `LogiskAdress`
WHERE logiskAdressat_id IN (
 SELECT id FROM `LogiskAdressat` adressat
 WHERE adressat.hsaId = 'HSA-ID-1'
);

DELETE FROM `LogiskAdress`
WHERE logiskAdressat_id IN (
 SELECT id FROM `LogiskAdressat` adressat
 WHERE adressat.hsaId = 'HSA-ID-2'
);

DELETE FROM `LogiskAdress`
WHERE logiskAdressat_id IN (
 SELECT id FROM `LogiskAdressat` adressat
 WHERE adressat.hsaId = 'HSA-ID-3'
);

DELETE FROM `LogiskAdress`
WHERE logiskAdressat_id IN (
 SELECT id FROM `LogiskAdressat` adressat
 WHERE adressat.hsaId = 'HSA-ID-4'
);

DELETE FROM `LogiskAdress`
WHERE logiskAdressat_id IN (
 SELECT id FROM `LogiskAdressat` adressat
 WHERE adressat.hsaId = 'HSA-ID-5'
);

DELETE FROM `LogiskAdress`
WHERE logiskAdressat_id IN (
 SELECT id FROM `LogiskAdressat` adressat
 WHERE adressat.hsaId = 'HSA-ID-6'
);

-- Ta bort producent
Delete FROM `Tjanstekomponent`
WHERE hsaId = CONCAT('HSA-ID-TESTSTUBBE-',@popularDomainName);


-- Ta bort logiska adressater
DELETE FROM `LogiskAdressat`
WHERE hsaId = 'HSA-ID-1';

DELETE FROM `LogiskAdressat`
WHERE hsaId = 'HSA-ID-2';

DELETE FROM `LogiskAdressat`
WHERE hsaId = 'HSA-ID-3';

DELETE FROM `LogiskAdressat`
WHERE hsaId = 'HSA-ID-4';

DELETE FROM `LogiskAdressat`
WHERE hsaId = 'HSA-ID-5';

DELETE FROM `LogiskAdressat`
WHERE hsaId = 'HSA-ID-6';