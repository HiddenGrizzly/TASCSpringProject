import { AfterViewInit, Component, ComponentFactoryResolver, ElementRef, OnDestroy, Type, ViewChild, ViewContainerRef } from '@angular/core';
import { Subscription } from 'rxjs';
import { ModalService } from 'src/app/services/modal/modal.service';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements AfterViewInit, OnDestroy {

  @ViewChild('glModal') glModal!: ElementRef;

  private modalSubscription!: Subscription;

  constructor(
    private modalService: ModalService
  ) { }

  ngAfterViewInit(): void {
    const dialog: HTMLDialogElement = this.glModal.nativeElement;
    this.modalSubscription = this.modalService.observeModal().subscribe(res => {
      if (res) {
        this.showModal(dialog)
      } else {
        this.hideModal(dialog);
      }
    });
  }

  ngOnDestroy(): void {
    if (this.modalSubscription) {
      this.modalSubscription.unsubscribe();
    }
  }


  showModal(dialog: HTMLDialogElement) {
    dialog.showModal();
  }

  hideModal(dialog: HTMLDialogElement) {
    dialog.close();
  }

  onClose() {
    this.modalService.close();
  }

  doFormSubmit() {
    console.log('hello');
    this.onClose();
  }

}
