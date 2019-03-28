const api = 'http://localhost:8080';
const findAll = '/listAll';
const save = '/save';
const process = '/process';

export const environment = {
  production: false,
  visit: {
    process: api + '/visit' + process
  },
  employee: {
    listAll: api + '/employee' + findAll,
    save: api + '/employee' + save
  }
};
