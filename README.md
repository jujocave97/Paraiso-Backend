# Paraiso-Backend
Parte backend del sitio web de la reposteria El Paraiso de Andrea

🔐 Autenticación
POST /api/auth/register → Registro de usuario

POST /api/auth/login → Login, devuelve JWT

👤 Usuarios
GET /api/users/ → Listar todos (requiere token, probablemente admin)

GET /api/users/{email} → Info de un usuario (admin o él mismo)

PUT /api/users/{email} → Editar usuario

DELETE /api/users/{email} → Eliminar usuario

🍰 Tartas
GET /api/cakes/ → Listar tartas

POST /api/cakes/create → Crear tarta (admin)

PUT /api/cakes/{id} → Editar tarta (admin)

DELETE /api/cakes/{id} → Eliminar tarta (admin)

📅 Reservas
GET /api/reservas/ → Listar todas las reservas (admin)

GET /api/reservas/{id} → Reservas de usuario

POST /api/reservas/reservar → Reservar tarta

PUT /api/reservas/{id}/estado/{estado} → Cambiar estado (admin)

DELETE /api/reservas/{reservaID} → Eliminar reserva (admin)