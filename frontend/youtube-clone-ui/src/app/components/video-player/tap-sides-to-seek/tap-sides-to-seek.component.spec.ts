import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TapSidesToSeekComponent } from './tap-sides-to-seek.component';

describe('TapSidesToSeekComponent', () => {
  let component: TapSidesToSeekComponent;
  let fixture: ComponentFixture<TapSidesToSeekComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TapSidesToSeekComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TapSidesToSeekComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
