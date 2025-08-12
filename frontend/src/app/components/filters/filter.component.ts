import {Component, input, output, signal} from '@angular/core';
import {MemoService} from '../../memo.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
})
export class FilterComponent {

  triggerMemoList = output();
  triggerAddForm = output();

  activateMemoList(): void {
    this.triggerMemoList.emit();
  }

  activateAddForm(): void {
    this.triggerAddForm.emit();
  }

}

