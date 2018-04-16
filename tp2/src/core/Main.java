package core;

import gridLock.GridLockProblem;
import gridLock.GridLockState;
import searchAlgorithms.BreadthFirstSearch;

public class Main {

	public static void main(String[] args) {
		SearchEngine<GridLockState> searchEngine = new SearchEngine<>();
		searchEngine.search(new BreadthFirstSearch<GridLockState>(), new GridLockProblem());
	}

}
