import { Store } from './store.model';
export class Visit{
    constructor(
        public id: number,
        public distance: number,
        public store: Store
    ){}
}
