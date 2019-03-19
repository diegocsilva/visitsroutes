import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitNewComponent } from './visit-new.component';

describe('VisitNewComponent', () => {
  let component: VisitNewComponent;
  let fixture: ComponentFixture<VisitNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
