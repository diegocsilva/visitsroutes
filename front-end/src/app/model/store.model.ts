import { Coordinate } from './coordinate.model';
export class Store{
    constructor(
        public id: number,
        public name: string,
        public coordinate: Coordinate
    ) {}
}
