import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilSetupComponent } from './profil-setup.component';

describe('ProfilSetupComponent', () => {
  let component: ProfilSetupComponent;
  let fixture: ComponentFixture<ProfilSetupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfilSetupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfilSetupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
