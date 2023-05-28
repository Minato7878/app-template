FROM node:18

WORKDIR /usr/src/app/app-ui

COPY package*.json ./

RUN npm install -g @angular/cli @angular-devkit/build-angular && npm install

COPY . .

EXPOSE 4200

CMD ["npm", "start"]