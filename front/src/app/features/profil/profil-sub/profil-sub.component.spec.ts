import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilSubComponent } from './profil-sub.component';

describe('ProfilSubComponent', () => {
  let component: ProfilSubComponent;
  let fixture: ComponentFixture<ProfilSubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfilSubComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfilSubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
