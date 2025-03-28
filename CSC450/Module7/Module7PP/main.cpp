#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable> //new tech for this week - conditional variables
using namespace std;

mutex mut; //create a global mutex to lock program memory for threads
condition_variable condv; //create a condition variable for concurrency
int counter = 0; //the variable that will be counting up and down
bool done = false; //a thread control boolean to be used with the conditional var

void counter1(){ //counting up thread
	lock_guard<mutex> lock(mut); //simple mutex lock, since this is the first thread to run we can use a basic
								 //lock object.
	while(counter <= 19){
		cout << counter << endl;
		counter++;
	}

	done = true; //set our boolean to true, this indicates that thread2 is good to go
	condv.notify_all(); //this conditional variable method notifies other threads that the thread is complete.
}

void counter2(){ //counting down thread
	unique_lock<mutex> lock(mut); //fancy lock method - we're using this because it can wait to lock until done==true

	while(done == false){
		condv.wait(lock); //wait() sleeps the thread until done is true
	}
	while(counter >= 0){
		cout << counter << endl;
		counter--;
	}
}

int main(){
	thread t1(counter1);
	thread t2(counter2);
	t1.join(); //joining both threads at the same time.
	t2.join();
	return 0;
}
