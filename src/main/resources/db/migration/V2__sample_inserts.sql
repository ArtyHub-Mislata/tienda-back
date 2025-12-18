-- =========================
-- INSERT CATEGORIES
-- =========================
INSERT INTO categories (nombre) VALUES
                                    ('Pintura'),
                                    ('Escultura'),
                                    ('Fotografía'),
                                    ('Arte Digital'),
                                    ('Ilustración');

-- =========================
-- INSERT USERS
-- =========================
INSERT INTO users (
    name, email, password, n_account,
    description, address, image_profile_url, role
) VALUES
      ('Admin Root', 'admin@artyhub.com', 'admin123', '9999999999999999',
       'Administrador del sistema', 'Madrid', 'https://www.flaticon.es/icono-gratis/administrador-de-sistema_5322033', 'ADMIN'),

      ('Ana Torres', 'ana@artyhub.com', 'password123', '1234567890123456',
       'Artista contemporánea', 'Madrid', 'https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?crop=faces&fit=crop&w=200&h=200', 'USER'),

      ('Luis Gómez', 'luis@artyhub.com', 'password123', '2234567890123456',
       'Escultor profesional', 'Barcelona', 'https://images.unsplash.com/photo-1531123897727-8f129e1688ce?crop=faces&fit=crop&w=200&h=200', 'USER'),

      ('María López', 'maria@artyhub.com', 'password123', '3234567890123456',
       'Fotógrafa urbana', 'Valencia', 'https://images.unsplash.com/photo-1524504388940-b1c1722653e1?crop=faces&fit=crop&w=200&h=200', 'USER'),

      ('Carlos Ruiz', 'carlos@artyhub.com', 'password123', '4234567890123456',
       'Diseñador digital', 'Sevilla', 'https://images.unsplash.com/photo-1520813792240-56fc4a3765a7?crop=faces&fit=crop&w=200&h=200', 'USER');

-- =========================
-- INSERT ARTWORKS
-- =========================
INSERT INTO artworks (
    name, description, img_url, price, categoria_id, user_id
) VALUES
      ('Atardecer Rojo', 'Pintura acrílica sobre lienzo', 'https://services.meteored.com/img/article/perche-sole-e-luna-si-tingono-di-rosso-all-alba-e-al-tramonto-1723049784862_1280.png', 150.00, 1, 2),
      ('Reflejos Urbanos', 'Escena nocturna de ciudad', 'https://images.unsplash.com/photo-1494526585095-c41746248156?fit=crop&w=400&h=300', 220.50, 1, 2),

      ('Figura en Mármol', 'Escultura clásica en mármol', 'https://images.unsplash.com/photo-1570129477492-45c003edd2be?fit=crop&w=400&h=300', 950.00, 2, 3),
      ('El Pensador Moderno', 'Escultura abstracta contemporánea', 'https://images.unsplash.com/photo-1607746882042-944635dfe10e?fit=crop&w=400&h=300', 780.00, 2, 3),

      ('Luces de Calle', 'Fotografía nocturna urbana', 'https://images.unsplash.com/photo-1501594907352-04cda38ebc29?fit=crop&w=400&h=300', 120.00, 3, 4),
      ('Sombras y Contrastes', 'Fotografía en blanco y negro', 'https://images.unsplash.com/photo-1462331940025-496dfbfc7564?fit=crop&w=400&h=300', 140.00, 3, 4),

      ('Mundo Pixelado', 'Ilustración digital futurista', 'https://www.freepik.es/vector-premium/pixel-tierra-miniatura-8-bits-mundo-ilustraciones-vectoriales-globo-terraqueo-arte-pixeles_24430109.htm', 200.00, 4, 5),
      ('Geometría Viva', 'Arte digital geométrico', 'https://www.freepik.es/vectores/geometria-viva/2', 180.00, 4, 5),

      ('Personaje Fantástico', 'Ilustración de fantasía épica', 'https://www.xataka.com/n/mejores-desconocidas-obras-fantasia-epica-contemporanea', 95.00, 5, 2),
      ('Naturaleza Abstracta', 'Composición ilustrada moderna', 'https://pixers.es/fotomurales/naturaleza-abstracta-27992187', 110.00, 5, 5);