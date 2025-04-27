#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <time.h>
using namespace std;

mutex mut;
condition_variable condv;
int counter = 0;
bool done = false;

void counter1(){
	lock_guard<mutex> lock(mut);
	while(counter <= 19){
		cout << counter << endl;
		counter++;
	}

	done = true;
	condv.notify_all();
}

void counter2(){
	unique_lock<mutex> lock(mut);

	while(done == false){
		condv.wait(lock);
	}
	while(counter >= 0){
		cout << counter << endl;
		counter--;
	}
}

int main(){
	auto starttime = std::chrono::steady_clock::now();
	thread t1(counter1);
	thread t2(counter2);
	t1.join();
	t2.join();
	auto endtime = std::chrono::steady_clock::now();
	auto totaltime = endtime - starttime;
	cout << std::chrono::duration<double, std::milli>(totaltime).count() << endl;
	return 0;
}
