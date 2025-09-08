# Stage 1: build
FROM node:20-alpine AS build
WORKDIR /app

# Copier uniquement package.json et package-lock.json d'abord
COPY package*.json ./

# Installer les dépendances
RUN npm install -g npm@11

# Copier le reste des fichiers
COPY . .

# Build Angular
RUN npm run build --prod

# Stage 2: serveur Nginx
FROM nginx:alpine
COPY --from=build /app/dist/* /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

