const api = 'http://localhost:8080';
const findAll = 'findAll';
const save = 'save';
const process = 'process';

export const environment = {
  production: true,
  visit: {
    process: api + 'visit' + process
  },
  employee: {
    listAll: api + 'employee' + findAll,
    save: api + 'employee' + save
  }
};
