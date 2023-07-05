insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(1,'800 g de filetes finos de carne magra de ternera',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(2,'1 pimiento rojo pequeño',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(3,"2 zanahoria",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(4,"1 cebolleta pequeña",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(5,"4 dientes de ajo",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(6,"1 guindilla pequeña",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(7,"2 cogollos de lechuga bien crujiente",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(8,"300g perejil fresco",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(9,"1 cucharada de sal",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(10,"1 cucharada de aceite de oliva",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(11,"1 cucharada de pimienta negra",1);

insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(12,"4 filetes de tilapia sin espinas y sin escamas",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(13,"2 cucharadas de jugo de limón",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(14,"1 cucharada de alcaparras",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(15,"2 cucharadas de jugo de limón",1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(16,"1/4 manojo de perejil finamente picado ",1);


insert into recetas (rec_id, rec_nombre, rec_categoria, rec_preparacion, rec_resumen, rec_imagen, rec_estado) values(1,'Tacos de lechuga con ternera salteada','Carnes','Serviremos estos wraps o tacos de lechuga con ternera','La carne se puede preparar de diferentes maneras.Descúbrelas con nuestras recetas','ternera.png',1);

insert into recetas (rec_id, rec_nombre, rec_categoria, rec_preparacion, rec_resumen, rec_imagen, rec_estado) values(2,'Pescado en salsa de limón con ajo y perejil','Pescados','SIRVE los filetes de pescado bañados en la salsa','Descubre las recetas deliciosa que se puede preparar con pescado','pescado.jpg',1);


insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(1,1);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(2,1);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(3,1);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(4,1);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(5,1);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(6,1);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(7,1);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(8,1);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(9,1);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(10,1);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(11,1);

insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(5,2);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(8,2);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(9,2);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(10,2);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(11,2);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(12,2);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(13,2);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(14,2);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(15,2);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(16,2);


