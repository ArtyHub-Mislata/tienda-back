-- =========================
-- TABLE: categories
-- =========================
CREATE TABLE categories (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nombre VARCHAR(255) NOT NULL
);

-- =========================
-- TABLE: users
-- =========================
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       n_account VARCHAR(255) NOT NULL,
                       description VARCHAR(255),
                       address VARCHAR(255),
                       image_profile_url VARCHAR(255),
                       role VARCHAR(50) NOT NULL
);

-- =========================
-- TABLE: artworks
-- =========================
CREATE TABLE artworks (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description VARCHAR(255),
                          img_url VARCHAR(255),
                          price DECIMAL(10,2),

                          categoria_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL,

                          CONSTRAINT fk_artworks_category
                              FOREIGN KEY (categoria_id)
                                  REFERENCES categories (id),

                          CONSTRAINT fk_artworks_user
                              FOREIGN KEY (user_id)
                                  REFERENCES users (id)
);
