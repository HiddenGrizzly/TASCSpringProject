import { ModalService } from 'src/app/services/modal/modal.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserRes } from 'src/app/models/users/UserRes';
import { SelfService } from 'src/app/services/self/self.service';
import { ChangePasswordFormComponent } from 'src/app/components/change-password-form/change-password-form.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user!: UserRes;

  userForm!: FormGroup;

  imageUrl!: string;

  image: File | null = null;

  constructor(
    private selfService: SelfService,
    private fb: FormBuilder,
    private modalService: ModalService
  ) {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required]
    })
  }

  ngOnInit(): void {
    this.selfService.getUserProfile().subscribe(res => {
      console.log(res);
      this.user = res;
      this.imageUrl = res.avatar;
      this.setDefaultValues();
    })
  }

  setDefaultValues(event?: Event): void {
    if (event) {
      event.preventDefault();
    }
    this.userForm.patchValue({
      username: this.user.username,
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      email: this.user.email
    })
  }

  onUpdateUser(): void {
    if (this.userForm.valid) {
      this.selfService.updateUser(this.userForm.value).subscribe(res => {
        this.user = res;
      })
    }
  }

  openChangePasswordForm() {
    this.modalService.open();
  }

  onImageSelect(event: Event) {
    const target = event.target as HTMLInputElement;
    if (target.files) {
      this.image = target.files[0];
      let reader = new FileReader();
      reader.readAsDataURL(this.image);
      reader.onload = (e: any) => {
        this.imageUrl = e.target.result
      }
    }
  }

  uploadImage() {
    if (this.image) {
      console.log('image available');
      const formData = new FormData();
      formData.append('avatar', this.image);
      this.selfService.changeAvatar(formData).subscribe();
    }
  }

}
