import { Injectable } from '@angular/core';
import { NotifierService } from 'angular-notifier';

@Injectable()
export class NotifyComponent {


  private notifier: NotifierService;

  public constructor( notifier: NotifierService ) {
    this.notifier = notifier;
  }

  public showNotification( type: string, message: string ): void {
    this.notifier.notify( type, message );
  }
}
