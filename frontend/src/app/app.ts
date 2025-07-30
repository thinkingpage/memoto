import {ChangeDetectionStrategy, Component, input, OnInit, signal} from '@angular/core';
import { RouterOutlet } from '@angular/router';

import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {FilterComponent} from './components/filters/filter.component';
import {MemoService} from './memo.service';
import {MemoModel} from './models/memo.model';
import {HttpErrorResponse} from '@angular/common/http';
import {MemoAllMemosComponent} from './components/memos/memo-all-memos.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    HeaderComponent,
    FilterComponent,
    MemoAllMemosComponent,
    FooterComponent
  ],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  protected readonly title = signal('memoto');

  constructor(private memoService: MemoService) {}

  showAllMemos = signal(false);
  memos = signal<MemoModel[]>([]);

// type not needed (bool)
  getAllMemosAPP() {
    this.showAllMemos.update(value => !value);
    if(!this.showAllMemos()) {
      return;
    }
    this.memoService.getMemos().subscribe({
      next: (response: MemoModel[]) => {
        console.log("haaa gotyy")
        this.memos.set(response);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    });
  }

  addMemo(memo: MemoModel) {
    this.memoService.addMemo(memo).subscribe({
        next: (response: MemoModel) => {
          console.log(response);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error.message);
        },
      }
    )
  }
}
