import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserMovieCardComponent } from './user-movie-card.component';

describe('UserMovieCardComponent', () => {
  let component: UserMovieCardComponent;
  let fixture: ComponentFixture<UserMovieCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserMovieCardComponent]
    });
    fixture = TestBed.createComponent(UserMovieCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
