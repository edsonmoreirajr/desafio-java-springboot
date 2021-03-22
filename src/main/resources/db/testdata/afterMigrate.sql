set foreign_key_checks = 0;

delete from product;

set foreign_key_checks = 1;

alter table product auto_increment = 1;

INSERT INTO `product` VALUES (1,'Malbec Desodorante Colônia','Malbec é uma fragrância masculina da família das Amadeiradas.É única e desenvolvida através de um processo de fabricação exclusivo no mundo.','154.90');

INSERT INTO `product` VALUES (2,'Natura Homem','Sinta o frescor da combinação de ervas aromáticas com o gengibre e noz moscada, equilibrado com um toque de madeiras quentes, desperta e renova.', '67.40');

INSERT INTO `product` VALUES (3,'Presente Natura Queridinhos','Sève, Ekos, Tododia e Erva Doce unidos para turbinar a rotina de cuidados de quem você ama.','79.90');
