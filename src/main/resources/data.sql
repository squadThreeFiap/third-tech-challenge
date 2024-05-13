INSERT INTO restaurantes (id, nome, tipo_cozinha, cep, endereco, uf, cidade, bairro, numero, dias_funcionamentos, hora_abertura, hora_enceramento, capacidade)
VALUES
    (1010, 'Sabor do Brasil', 'Brasileira', '98765-432', 'Rua das Tapiocas', 'MG', 'Belo Horizonte', 'Savassi', 300, '{SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA,SABADO}', '11:30:00', '21:30:00', 120),
    (2020, 'La Trattoria', 'Italiana', '12345-678', 'Rua das Massas', 'SP', 'São Paulo', 'Centro', 100, '{SEGUNDA, TERCA}', '08:00:00', '22:00:00', 50),
    (3030, 'Bamboo Garden', 'Chinesa', '54321-987', 'Avenida dos Bambus', 'RJ', 'Rio de Janeiro', 'Copacabana', 200, '{QUARTA, QUINTA}', '10:00:00', '23:00:00', 80),
    (4040, 'El Jalapeño', 'Mexicana', '76543-210', 'Rua dos Nachos', 'RS', 'Porto Alegre', 'Moinhos de Vento', 400, '{DOMINGO}', '12:00:00', '20:00:00', 60),
    (5050, 'Sushi House', 'Japonesa', '54321-123', 'Alameda dos Sushis', 'SP', 'São Paulo', 'Vila Olímpia', 500, '{SEGUNDA, QUARTA, SEXTA}', '18:00:00', '23:30', 90),
    (6060, 'Tandoori Nights', 'Indiana', '87654-321', 'Rua das Especiarias', 'PR', 'Curitiba', 'Batel', 600, '{TERCA, QUINTA}', '19:00:00', '22:00:00', 70),
    (7070, 'Le Petit Bistro', 'Francesa', '23456-789', 'Avenue des Croissants', 'RJ', 'Rio de Janeiro', 'Leblon', 700, '{QUARTA, SEXTA}', '20:00:00', '00:00:00', 100);

INSERT INTO usuarios (id, cpf, nome, ddd, telefone, email, data_cadastro)
VALUES
    (1105, '12345678901', 'John Doe', '11', '12345678', 'john.doe@example.com', '2024-05-11'),
    (2105, '23456789012', 'Jane Doe', '12', '23456789', 'jane.doe@example.com', '2024-05-12'),
    (3105, '34567890123', 'Jake Doe', '13', '34567890', 'jim.smith@example.com', '2024-05-13');

INSERT INTO reservas (id, usuario_id, restaurante_id, quantidade_pessoas, data_hora_inicio, data_hora_fim, status)
VALUES
    (1, 1105, 1010, 6, '2023-03-15 13:00:00', '2023-03-15 14:30:00', 'CONCLUIDA'),
    (2, 1105, 1010, 8, '2023-05-15 12:00:00', '2023-05-15 13:00:00', 'CANCELADA'),
    (3, 1105, 1010, 10, '2023-06-15 19:00:00', '2023-06-15 21:00:00', 'AGENDADO'),
    (4, 2105, 1010, 10, '2023-06-15 19:00:00', '2023-06-15 21:00:00', 'AGENDADO'),
    (5, 2105, 1010, 4, '2023-06-15 18:30:00', '2023-06-15 21:00:00', 'AGENDADO'),
    (6, 3105, 1010, 2, '2023-06-15 19:00:00', '2023-06-15 22:00:00', 'AGENDADO'),
    (7, 2105, 3030, 2, '2023-04-10 18:30:00', '2023-04-10 20:30:00', 'CANCELADA'),
    (8, 2105, 5050, 2, '2023-04-10 19:00:00', '2023-03-16 21:00:00', 'CONCLUIDA'),
    (9, 2105, 6060, 2, '2023-06-16 18:30:00', '2023-06-16 20:30:00', 'AGENDADO'),
    (10, 3105, 2020, 1, '2023-04-17 12:00:00', '2023-04-17 14:00:00', 'CONCLUIDA'),
    (11, 3105, 7070, 2, '2023-05-05 12:00:00', '2023-05-05 14:00:00', 'CANCELADA'),
    (12, 3105, 4040, 4, '2023-07-13 19:00:00', '2023-03-17 20:30:00', 'AGENDADO');