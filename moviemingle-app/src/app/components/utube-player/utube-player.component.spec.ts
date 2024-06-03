import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UtubePlayerComponent } from './utube-player.component';

describe('UtubePlayerComponent', () => {
  let component: UtubePlayerComponent;
  let fixture: ComponentFixture<UtubePlayerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UtubePlayerComponent]
    });
    fixture = TestBed.createComponent(UtubePlayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
