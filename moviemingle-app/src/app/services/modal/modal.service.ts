import { Injectable, Type } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ModalService {

  private modalTriggerSubject$: BehaviorSubject<boolean> = new BehaviorSubject(false);


  constructor() {
  }

  observeModal(): Observable<boolean> {
    return this.modalTriggerSubject$.asObservable();
  }

  open() {
    this.modalTriggerSubject$.next(true);
  }

  close() {
    this.modalTriggerSubject$.next(false);
  }

}
