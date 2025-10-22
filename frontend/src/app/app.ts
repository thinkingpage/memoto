import {Component, effect, OnInit, signal} from '@angular/core';
import {RouterOutlet} from '@angular/router';

import {MemoService} from './memo.service';
import {MemoModel} from './models/memo.model';
import {HttpErrorResponse} from '@angular/common/http';
import {MemoDTO} from './models/memo.dto.model';
import {MemoStateService} from './components/memos/memo-state.service';
import {HeaderComponent} from './components/header/header.component';
import {MenuComponent} from './components/menu.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    HeaderComponent,
    MenuComponent,
  ],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App implements OnInit {
  protected readonly title = signal('memoto');
  memos = signal<MemoDTO[]>([]);
  memo = signal<MemoModel | null>(null);
  errorMessage = signal<string | null>(null);
  userColor = signal<string>('#ECE3CA');

  constructor(
    private memoService: MemoService,
    private memoStateService: MemoStateService
  ) {}

  ngOnInit() {
    this.loadMemos();
  }

  loadMemos() {
    this.memoService.getMemos().subscribe({
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
      },
      next: (response: MemoDTO[]) => {
        this.memos.set(response);
        this.memoStateService.memos.set(response)
      }
    })
  }

  addMemo(memo: MemoModel) {
    this.memoService.addMemo(memo).subscribe({
        next: (response: MemoDTO) => {
          this.memos.update(value => {
            return [...value, response];
          });
        },
        error: (error: HttpErrorResponse) => {
          console.log("errormessage: " + error.message);
          this.errorMessage.set(error.message);
          return error;
        },
      }
    )
  }

  memoById(id: number): void {
    this.memoService.getMemo(id).subscribe({
      next: (memo: MemoModel) => {
        this.memo.set(memo);
      },
      error: (error: HttpErrorResponse) => console.log(error.message)
    })
  }
}
