import { TestBed } from '@angular/core/testing';

import { ListaccountService } from './listaccount.service';

describe('ListaccountService', () => {
  let service: ListaccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListaccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
