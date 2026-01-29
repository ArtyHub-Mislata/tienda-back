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
    name, email, password,
    description, address, image_profile_url, role
) VALUES
      ('Admin Root', 'admin@artyhub.com', 'admin123', 'Administrador del sistema', 'Madrid', 'https://images.unsplash.com/photo-1560250097-0b93528c311a?fit=crop&w=200&h=200', 'ADMIN'),
      ('Ana Torres', 'ana@artyhub.com', 'password123', 'Artista contemporánea', 'Madrid', 'https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?crop=faces&fit=crop&w=200&h=200', 'USER'),
      ('Luis Gómez', 'luis@artyhub.com', 'password123', 'Escultor profesional', 'Barcelona', 'https://images.unsplash.com/photo-1531123897727-8f129e1688ce?crop=faces&fit=crop&w=200&h=200', 'USER'),
      ('María López', 'maria@artyhub.com', 'password123', 'Fotógrafa urbana', 'Valencia', 'https://images.unsplash.com/photo-1524504388940-b1c1722653e1?crop=faces&fit=crop&w=200&h=200', 'USER'),
      ('Carlos Ruiz', 'carlos@artyhub.com', 'password123', 'Diseñador digital', 'Sevilla', 'https://images.unsplash.com/photo-1520813792240-56fc4a3765a7?crop=faces&fit=crop&w=200&h=200', 'USER');

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

      ('Mundo Pixelado', 'Ilustración digital futurista', 'https://images.unsplash.com/photo-1518770660439-4636190af475?fit=crop&w=400&h=300', 200.00, 4, 5),
      ('Geometría Viva', 'Arte digital geométrico', 'https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?fit=crop&w=400&h=300', 180.00, 4, 5),

      ('Personaje Fantástico', 'Ilustración de fantasía épica', 'https://images.unsplash.com/photo-1520975916090-3105956dac38?fit=crop&w=400&h=300', 95.00, 5, 2),
      ('Naturaleza Abstracta', 'Composición ilustrada moderna', 'https://images.unsplash.com/photo-1501785888041-af3ef285b470?fit=crop&w=400&h=300', 110.00, 5, 5),

      ('Nebulosa Profunda', 'Exploración de color espacial', 'https://picsum.photos/id/209/400/300', 310.00, 4, 5),
      ('Arquitectura Minimalista', 'Estudio de líneas y luz', 'https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?fit=crop&w=400&h=300', 250.00, 3, 4),

      ('Océano Infinito', 'Óleo sobre madera', 'https://picsum.photos/id/247/400/300', 420.00, 1, 2),
      ('Estructura de Hierro', 'Escultura industrial pesada', 'https://images.unsplash.com/photo-1518709268805-4e9042af9f23?fit=crop&w=400&h=300', 1200.00, 2, 3),

      ('Retrato de Humo', 'Arte conceptual digital', 'https://images.unsplash.com/photo-1550684848-fac1c5b4e853?fit=crop&w=400&h=300', 175.00, 4, 2),
      ('Bosque de Cristal', 'Ilustración detallada de naturaleza', 'https://images.unsplash.com/photo-1448375240586-882707db888b?fit=crop&w=400&h=300', 85.00, 5, 5),

      ('Mirada Urbana', 'Fotografía callejera espontánea', 'https://images.unsplash.com/photo-1477959858617-67f85cf4f1df?fit=crop&w=400&h=300', 190.00, 3, 4),
      ('Esferas de Neón', 'Composición 3D abstracta', 'https://images.unsplash.com/photo-1550745165-9bc0b252726f?fit=crop&w=400&h=300', 290.00, 4, 3);