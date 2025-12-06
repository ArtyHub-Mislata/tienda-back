-- =====================================================
-- CATEGORIES
-- =====================================================
INSERT INTO categories (id, nombre) VALUES
                                        (1, 'Pintura'),
                                        (2, 'Escultura'),
                                        (3, 'Ilustración'),
                                        (4, 'Fotografía'),
                                        (5, 'Arte Digital');

-- =====================================================
-- ARTWORKS
-- =====================================================
INSERT INTO artworks (id, name, description, img_url, price, stock, categoria_id) VALUES
                                                                                      (1, 'Atardecer Mediterráneo',
                                                                                       'Pintura al óleo inspirada en los colores del mar al final del día.',
                                                                                       'https://example.com/images/artworks/atardecer_mediterraneo.jpg',
                                                                                       120.50, 10, 1),

                                                                                      (2, 'Bosque en Otoño',
                                                                                       'Obra impresionista que representa un bosque durante el cambio de estación.',
                                                                                       'https://example.com/images/artworks/bosque_otono.jpg',
                                                                                       180.00, 8, 1),

                                                                                      (3, 'Forma Humana',
                                                                                       'Escultura abstracta realizada en resina con líneas suaves.',
                                                                                       'https://example.com/images/artworks/forma_humana.jpg',
                                                                                       320.00, 4, 2),

                                                                                      (4, 'Equilibrio',
                                                                                       'Escultura minimalista en metal que juega con el balance y el espacio.',
                                                                                       'https://example.com/images/artworks/equilibrio.jpg',
                                                                                       450.00, 2, 2),

                                                                                      (5, 'Rostro Urbano',
                                                                                       'Ilustración digital que refleja la vida en la ciudad.',
                                                                                       'https://example.com/images/artworks/rostro_urbano.jpg',
                                                                                       60.00, 25, 3),

                                                                                      (6, 'Personaje Fantástico',
                                                                                       'Ilustración de estilo cartoon con colores vivos.',
                                                                                       'https://example.com/images/artworks/personaje_fantastico.jpg',
                                                                                       40.00, 30, 3),

                                                                                      (7, 'Luz y Sombra',
                                                                                       'Fotografía en blanco y negro con alto contraste.',
                                                                                       'https://example.com/images/artworks/luz_sombra.jpg',
                                                                                       75.00, 15, 4),

                                                                                      (8, 'Reflejos',
                                                                                       'Fotografía urbana capturando reflejos en superficies de cristal.',
                                                                                       'https://example.com/images/artworks/reflejos.jpg',
                                                                                       90.00, 12, 4),

                                                                                      (9, 'Paisaje Digital',
                                                                                       'Arte digital creado con técnicas de pintura digital avanzada.',
                                                                                       'https://example.com/images/artworks/paisaje_digital.jpg',
                                                                                       110.00, 20, 5),

                                                                                      (10, 'Mundo Futurista',
                                                                                       'Ilustración digital ambientada en una ciudad futurista.',
                                                                                       'https://example.com/images/artworks/mundo_futurista.jpg',
                                                                                       150.00, 18, 5),

                                                                                      (11, 'Mar en Calma',
                                                                                       'Pintura acrílica que transmite serenidad y equilibrio.',
                                                                                       'https://example.com/images/artworks/mar_calma.jpg',
                                                                                       130.00, 9, 1),

                                                                                      (12, 'Energía Interna',
                                                                                       'Escultura expresiva que representa la fuerza interior.',
                                                                                       'https://example.com/images/artworks/energia_interna.jpg',
                                                                                       500.00, 3, 2),

                                                                                      (13, 'Mirada Profunda',
                                                                                       'Retrato ilustrado con gran nivel de detalle.',
                                                                                       'https://example.com/images/artworks/mirada_profunda.jpg',
                                                                                       85.00, 14, 3),

                                                                                      (14, 'Calle Nocturna',
                                                                                       'Fotografía nocturna de una ciudad iluminada.',
                                                                                       'https://example.com/images/artworks/calle_nocturna.jpg',
                                                                                       95.00, 16, 4),

                                                                                      (15, 'Concepto Abstracto',
                                                                                       'Composición digital basada en formas geométricas.',
                                                                                       'https://example.com/images/artworks/concepto_abstracto.jpg',
                                                                                       70.00, 22, 5);

-- =====================================================
-- USERS
-- =====================================================
INSERT INTO users (id, name, email, password, address, description, image_profile_url, n_account) VALUES
                                                                                                      (1, 'Ana López',
                                                                                                       'ana.lopez@email.com',
                                                                                                       'password123',
                                                                                                       'Calle Mayor 10, Madrid',
                                                                                                       'Artista especializada en pintura contemporánea.',
                                                                                                       'https://example.com/profiles/ana_lopez.jpg',
                                                                                                       '1234567890123456'),

                                                                                                      (2, 'Carlos Martín',
                                                                                                       'carlos.martin@email.com',
                                                                                                       'securepass456',
                                                                                                       'Avenida del Arte 25, Barcelona',
                                                                                                       'Escultor con más de 10 años de experiencia artística.',
                                                                                                       'https://example.com/profiles/carlos_martin.jpg',
                                                                                                       '6543210987654321'),

                                                                                                      (3, 'Laura Gómez',
                                                                                                       'laura.gomez@email.com',
                                                                                                       'ilustracion789',
                                                                                                       'Calle Creativa 7, Valencia',
                                                                                                       'Ilustradora digital y diseñadora gráfica.',
                                                                                                       'https://example.com/profiles/laura_gomez.jpg',
                                                                                                       '1111222233334444'),

                                                                                                      (4, 'Miguel Sánchez',
                                                                                                       'miguel.sanchez@email.com',
                                                                                                       'foto2024',
                                                                                                       'Plaza Central 3, Sevilla',
                                                                                                       'Fotógrafo urbano y documental.',
                                                                                                       'https://example.com/profiles/miguel_sanchez.jpg',
                                                                                                       '2222333344445555'),

                                                                                                      (5, 'Elena Ruiz',
                                                                                                       'elena.ruiz@email.com',
                                                                                                       'artedigital',
                                                                                                       'Calle Innovación 18, Bilbao',
                                                                                                       'Artista digital especializada en entornos virtuales.',
                                                                                                       'https://example.com/profiles/elena_ruiz.jpg',
                                                                                                       '3333444455556666');
