insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(1,'800 g de filetes finos de carne magra de ternera',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(2,'1 pimiento rojo pequeño',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(3,'2 zanahoria',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(4,'1 cebolleta pequeña',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(5,'2 dientes de ajo',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(6,'1 guindilla pequeña',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(7,'2 cogollos de lechuga bien crujiente',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(8,'300g perejil fresco',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(9,'1 cucharada de sal',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(10,'1 cucharada de aceite de oliva',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(11,'1 cucharada de pimienta negra',1);

insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(12,'4 filetes de tilapia sin espinas y sin escamas',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(13,'2 cucharadas de jugo de limón',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(14,'1 cucharada de alcaparras',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(15,'2 cucharadas de jugo de limón',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(16,'1/4 manojo de perejil finamente picado ',1);

insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(17,'1/2 escarola',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(18,'2 cucharada de pan rallado',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(19,'8 aceitunas verdes ',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(20,'1 cucharada de alcaparra ',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(21,'1 puñado de algas dulces ',1);
insert into ingredientes (ingre_id, ingre_nombre,ingre_estado) values(22,'1 cucharada de alcaparra ',1);


insert into recetas (rec_id, rec_nombre, rec_categoria, rec_preparacion, rec_resumen, rec_imagen, rec_estado) values(1,'Tacos de lechuga con ternera salteada','Carnes','Serviremos estos wraps o tacos de lechuga con ternera','La carne se puede preparar de diferentes maneras.Descúbrelas con nuestras recetas','ternera.png',1);

insert into recetas (rec_id, rec_nombre, rec_categoria, rec_preparacion, rec_resumen, rec_imagen, rec_estado) values(2,'Pescado en salsa de limón con ajo y perejil','Pescados','SIRVE los filetes de pescado bañados en la salsa.','Descubre las recetas deliciosas que se pueden preparar con pescado','pescado.jpg',1);

insert into recetas (rec_id, rec_nombre, rec_categoria, rec_preparacion, rec_resumen, rec_imagen, rec_estado) values(3,'Receta de Ensalada verde con escarola y olivas','Ensaladas','El primer paso para preparar esta rica ensalada fresca sera picar la lechuga escarola en tiras finas También corta finamente el ajo con la cebolla y ponlos a macerar en un bol con vinagre Espera unos minutos y añade el pan rallado en el bol','¡¡La cantidad de ensaladas que puedes llegar a preparar con nuestras recetas!!','ensalada.webp',1);


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

insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(17,3);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(4,3);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(5,3);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(18,3);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(19,3);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(22,3);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(21,3);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(9,3);
insert into recetas_ingredientes (ingredientes_ingre_id, recetas_rec_id) values(11,3);


insert into usuarios (usu_id, usu_nombre, usu_apellido, usu_email, usu_fecha_nacimiento, usu_telefono, usu_sexo, usu_estatura, usu_estado, usu_administrador) values(1, 'Francisco', 'Franco', 'franco@gmail.com','1999-08-05', '4244667890', 'Masculino', 1.9, 1, 1 );
insert into usuarios (usu_id, usu_nombre, usu_apellido, usu_email, usu_fecha_nacimiento, usu_telefono, usu_sexo, usu_estatura, usu_estado, usu_administrador) values(2, 'Maria', 'Lopez', 'lopez@gmail.com','2000-03-02', '4249967000', 'Femenino', 1.6, 1, 1 );
insert into usuarios (usu_id, usu_nombre, usu_apellido, usu_email, usu_fecha_nacimiento, usu_telefono, usu_sexo, usu_estatura, usu_estado, usu_administrador) values(3, 'Carlos', 'Rodriguez', 'rodriguez@gmail.com','1997-09-03', '4233007890', 'Masculino', 1.8, 1, 0 );
insert into usuarios (usu_id, usu_nombre, usu_apellido, usu_email, usu_fecha_nacimiento, usu_telefono, usu_sexo, usu_estatura, usu_estado, usu_administrador) values(4, 'Patricia', 'Vidal', 'vidal@gmail.com','2003-01-10', '4211777890', 'Femenino', 1.7, 1, 0 );
insert into usuarios (usu_id, usu_nombre, usu_apellido, usu_email, usu_fecha_nacimiento, usu_telefono, usu_sexo, usu_estatura, usu_estado, usu_administrador) values(5, 'Mauricio', 'Larreta', 'larreta@gmail.com','1997-10-11', '4211487890', 'Masculino', 1.8, 1, 0 );
insert into usuarios (usu_id, usu_nombre, usu_apellido, usu_email, usu_fecha_nacimiento, usu_telefono, usu_sexo, usu_estatura, usu_estado, usu_administrador) values(6, 'Eugenia', 'Fernandez', 'fernandez@gmail.com','1995-03-12', '4204223881', 'Femenino', 1.5, 1, 0 );

