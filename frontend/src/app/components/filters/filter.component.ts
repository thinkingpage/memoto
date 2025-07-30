import {Component, input, output, signal} from '@angular/core';
import {MemoService} from '../../memo.service';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-filter',
  imports: [],
  templateUrl: './filter.component.html',
})
export class FilterComponent {

  triggerMemoList = output();
  triggerAddForm = output();

  constructor(private memoService: MemoService) {}

  activateMemoList(): void {
    this.triggerMemoList.emit();
  }

  activateAddForm(): void {
    this.triggerAddForm.emit();
  }

}

