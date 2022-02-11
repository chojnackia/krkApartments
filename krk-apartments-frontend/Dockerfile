FROM node:15.13-alpine
WORKDIR /krk-apartments-frontend/src
# ENV PATH="./node_modules/ .bin:$PATH"
# COPY package.json ./
# COPY package-lock.json ./
COPY . .
RUN npm install
CMD ["npm", "start"]