import { SelfService } from './../../services/self/self.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { matchPassword } from 'src/app/utils/ValidationUtils';
import { ModalService } from 'src/app/services/modal/modal.service';

@Component({
  selector: 'app-change-password-form',
  templateUrl: './change-password-form.component.html',
  styleUrls: ['./change-password-form.component.css']
})
export class ChangePasswordFormComponent implements OnInit {

  changePassForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private selfService: SelfService,
    private modalService: ModalService
  ) { }

  ngOnInit(): void {
    this.changePassForm = this.fb.group({
      oldPassword: ['', [Validators.required]],
      newPassword: ['', [Validators.required]],
      reTypePassword: ['', [Validators.required]]
    },
      {
        validator: matchPassword('newPassword', 'reTypePassword')
      }
    );
  }

  submitChangePassword() {
    if (this.changePassForm.valid) {
      const formValues = { ...this.changePassForm.value };
      delete formValues.reTypePassword;
      this.selfService.changePassword(formValues).subscribe(res => {
        this.modalService.close();
      })
    }
  }

}
