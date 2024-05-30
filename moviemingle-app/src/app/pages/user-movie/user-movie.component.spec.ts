import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserMovieComponent } from './user-movie.component';

describe('UserMovieComponent', () => {
  let component: UserMovieComponent;
  let fixture: ComponentFixture<UserMovieComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserMovieComponent]
    });
    fixture = TestBed.createComponent(UserMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
