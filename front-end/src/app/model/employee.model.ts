import { Visit } from 'src/app/model/visit.model';
import { Coordinate } from './coordinate.model';
export class Employee{
    constructor(
        public id: number,
        public name: string,
        public latitude: number,
        public longitude: number,
        public visits: number,
        public visitList: Array<Visit>
    ) {}
}
