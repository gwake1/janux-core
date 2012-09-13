CREATE TABLE `geography_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `phoneCode` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `sortOrder` int(11) DEFAULT NULL,
  `visible` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `name` (`name`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `geography_state_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sortOrder` int(11) DEFAULT NULL,
  `visible` bit(1) NOT NULL DEFAULT b'1',
  `countryId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_StateProvince__Country` (`countryId`),
  CONSTRAINT `fk_StateProvince__Country` FOREIGN KEY (`countryId`) REFERENCES `geography_country` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_business_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchantAccountId` int(11) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `industryType` varchar(255) NOT NULL,
  `uuid` varchar(36) NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `updated` datetime NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `key_uuid` (`uuid`),
  KEY `fk_BusinessUnit__MerchantAccount` (`merchantAccountId`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_credit_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `businessUnitId` int(11) NOT NULL,
  `typeId` int(11) NOT NULL,
  `numberMasked` varchar(32) NOT NULL,
  `expDate` date NOT NULL,
  `secCode` varchar(255) DEFAULT NULL,
  `swiped` bit(1) NOT NULL DEFAULT b'0',
  `cardHolder` varchar(255) DEFAULT NULL,
  `line1` varchar(255) DEFAULT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `line3` varchar(255) DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `numberHash` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `uuid` varchar(36) NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `updated` datetime NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `key_uuid` (`uuid`),
  UNIQUE INDEX `key_unique_businessUnitId_numberHash` (`businessUnitId`, `numberHash`),
  KEY `fk_CreditCard__CreditCardType` (`typeId`),
  KEY `fk_CreditCard__BusinessUnit` (`businessUnitId`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_credit_card_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) NOT NULL,
  `description` varchar(32) DEFAULT NULL,
  `sortOrder` int(11) DEFAULT NULL,
  `updated` datetime NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_code` (`code`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_merchant_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(12) NOT NULL,
  `bankBin` varchar(6) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  `agentBankNum` varchar(6) DEFAULT NULL,
  `agentChainNum` varchar(6) DEFAULT NULL,
  `storeNum` varchar(4) DEFAULT NULL,
  `terminalId` varchar(8) DEFAULT NULL,
  `terminalNum` varchar(4) DEFAULT NULL,
  `line1` varchar(255) DEFAULT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `line3` varchar(255) DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `merchantPhone` varchar(32) DEFAULT NULL,
  `servicePhone` varchar(32) DEFAULT NULL,
  `timezone` varchar(255) DEFAULT NULL,
  `currency` varchar(3) DEFAULT NULL,
  `uuid` varchar(36) NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `updated` datetime NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_uuid` (`uuid`),
  UNIQUE KEY `key_number_bankBin` (`number`,`bankBin`)
) DEFAULT CHARSET=utf8;

ALTER TABLE pay_business_unit
	ADD CONSTRAINT `fk_BusinessUnit__MerchantAccount` FOREIGN KEY (`merchantAccountId`) REFERENCES `pay_merchant_account` (`id`);

ALTER TABLE pay_credit_card
   ADD CONSTRAINT `fk_CreditCard__CreditCardType` FOREIGN KEY (`typeId`) REFERENCES `pay_credit_card_type` (`id`)
	,ADD CONSTRAINT `fk_CreditCard__BusinessUnit` FOREIGN KEY (`businessUnitId`) REFERENCES `pay_business_unit` (`id`)
;


