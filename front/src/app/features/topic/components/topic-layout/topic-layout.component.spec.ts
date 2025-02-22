import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopicLayoutComponent } from './topic-layout.component';

describe('TopicLayoutComponent', () => {
  let component: TopicLayoutComponent;
  let fixture: ComponentFixture<TopicLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TopicLayoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TopicLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
