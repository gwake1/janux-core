CREATE TABLE `pay_transaction_response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `approved` bit(1) NOT NULL DEFAULT b'0',
  `originalBytes` tinyblob,
  `updated` datetime NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `businessUnitId` int(11) NOT NULL,
  `transactionResponseId` int(11) NOT NULL,
  `uuid` varchar(36) NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `updated` datetime NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_uuid` (`uuid`),
  KEY `fk_Transaction__TransactionResponse` (`transactionResponseId`),
  KEY `fk_Transaction__BusinessUnit` (`businessUnitId`),
  CONSTRAINT `fk_Transaction__BusinessUnit` FOREIGN KEY (`businessUnitId`) REFERENCES `pay_business_unit` (`id`),
  CONSTRAINT `fk_Transaction__TransactionResponse` FOREIGN KEY (`transactionResponseId`) REFERENCES `pay_transaction_response` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_authorization` (
  `id` int(11) NOT NULL,
  `amount` decimal(19,2) NOT NULL,
  `externalSourceId` varchar(255) NOT NULL,
  `creditCardId` int(11) NOT NULL,
  `batched` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `fk_Authorization__Transaction` (`id`),
  KEY `fk_Authorization__CreditCard` (`creditCardId`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_authorization_hotel` (
  `id` int(11) NOT NULL,
  `stayDuration` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_AuthorizationHotel__Transaction` (`id`),
  CONSTRAINT `fk_AuthorizationHotel__Transaction` FOREIGN KEY (`id`) REFERENCES `pay_authorization` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_authorization_modification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `amount` decimal(19,2) NOT NULL,
  `authorizationId` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL,
  `updated` datetime NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_AuthorizationModification__Authorization` (`authorizationId`),
  CONSTRAINT `fk_AuthorizationModification__Authorization` FOREIGN KEY (`authorizationId`) REFERENCES `pay_authorization` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_authorization_response` (
  `id` int(11) NOT NULL,
  `approvalCode` varchar(255) NOT NULL,
  `storeNumber` varchar(255) DEFAULT NULL,
  `terminalNumber` varchar(255) DEFAULT NULL,
  `requestedACI` varchar(255) NOT NULL,
  `returnedACI` varchar(255) NOT NULL,
  `authSourceCode` varchar(255) NOT NULL,
  `transactionSequenceNumber` varchar(255) DEFAULT NULL,
  `responseCode` varchar(255) NOT NULL,
  `avsResultCode` varchar(255) NOT NULL,
  `authResponseText` varchar(255) DEFAULT NULL,
  `retrievalReferenceNumber` varchar(255) NOT NULL,
  `transactionIdentifier` varchar(255) NOT NULL,
  `validationCode` varchar(255) NOT NULL,
  `localTransDate` varchar(255) NOT NULL,
  `localTransTime` varchar(255) NOT NULL,
  `reversal` bit(1) NOT NULL DEFAULT b'0',
  `offline` bit(1) NOT NULL DEFAULT b'0',
  `declined` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `fk_AuthorizationResponse__Transaction` (`id`),
  CONSTRAINT `fk_AuthorizationResponse__Transaction` FOREIGN KEY (`id`) REFERENCES `pay_transaction_response` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_settlement` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Settlement__Transaction` (`id`),
  CONSTRAINT `fk_Settlement__Transaction` FOREIGN KEY (`id`) REFERENCES `pay_transaction` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_settlement_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `amount` decimal(19,2) NOT NULL,
  `purchaseIdentifier` varchar(255) NOT NULL,
  `authorizationId` int(11) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `externalSourceId` varchar(255) NOT NULL,
  `creditCardId` int(11) NOT NULL,
  `businessUnitId` int(11) NOT NULL,
  `settlementId` int(11) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `uuid` varchar(36) NOT NULL,
  `updated` datetime NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_SettlementItem__Authorization` (`authorizationId`),
  KEY `fk_SettlementItem__Settlement` (`settlementId`),
  KEY `fk_SettlementItem__BusinessUnit` (`businessUnitId`),
  KEY `fk_SettlementItem__CreditCard` (`creditCardId`),
  CONSTRAINT `fk_SettlementItem__Authorization` FOREIGN KEY (`authorizationId`) REFERENCES `pay_authorization` (`id`),
  CONSTRAINT `fk_SettlementItem__BusinessUnit` FOREIGN KEY (`businessUnitId`) REFERENCES `pay_business_unit` (`id`),
  CONSTRAINT `fk_SettlementItem__CreditCard` FOREIGN KEY (`creditCardId`) REFERENCES `pay_credit_card` (`id`),
  CONSTRAINT `fk_SettlementItem__Settlement` FOREIGN KEY (`settlementId`) REFERENCES `pay_settlement` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_settlement_item_hotel` (
  `id` int(11) NOT NULL,
  `checkOutDate` datetime NOT NULL,
  `averageRate` decimal(19,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_SettlementHotelItem__SettlementItem` (`id`),
  CONSTRAINT `fk_SettlementHotelItem__SettlementItem` FOREIGN KEY (`id`) REFERENCES `pay_settlement_item` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_settlement_response` (
  `id` int(11) NOT NULL,
  `recordFormat` varchar(255) NOT NULL,
  `applicationType` varchar(255) NOT NULL,
  `routingID` varchar(255) NOT NULL,
  `recordType` varchar(255) NOT NULL,
  `batchRecordCount` varchar(255) NOT NULL,
  `batchNetDeposit` varchar(255) NOT NULL,
  `batchResponseCode` varchar(255) NOT NULL,
  `batchNumber` varchar(255) NOT NULL,
  `batchResponseText` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_SettlementResponse__Transaction` (`id`),
  CONSTRAINT `fk_SettlementResponse__Transaction` FOREIGN KEY (`id`) REFERENCES `pay_transaction_response` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `pay_batch_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `merchantAccountId` int(11) NOT NULL,
  `updated` datetime NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_BatchNumber__MerchantAccount` (`merchantAccountId`),
  CONSTRAINT `fk_BatchNumber__MerchantAccount` FOREIGN KEY (`merchantAccountId`) REFERENCES `pay_merchant_account` (`id`)
) DEFAULT CHARSET=utf8;


ALTER TABLE pay_authorization 
   ADD CONSTRAINT `fk_Authorization__CreditCard` FOREIGN KEY (`creditCardId`) REFERENCES `pay_credit_card` (`id`)
  ,ADD CONSTRAINT `fk_Authorization__Transaction` FOREIGN KEY (`id`) REFERENCES `pay_transaction` (`id`)
;

