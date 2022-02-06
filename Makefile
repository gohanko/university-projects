CC = g++

clean_object:
	del /s /q .\build\*.o

clean_all:
	del /s /q .\build\*

copy_assets:
	xcopy /E .\assets .\build\assets
	
copy_dependencies:
	copy .\library\SFML-2.5.1\bin\sfml-graphics-2.dll .\build\sfml-graphics-2.dll
	copy .\library\SFML-2.5.1\bin\sfml-system-2.dll .\build\sfml-system-2.dll
	copy .\library\SFML-2.5.1\bin\sfml-window-2.dll .\build\sfml-window-2.dll

build_app:
	$(CC) -I ./library/SFML-2.5.1/include -c ./src/chess/board.cpp -o ./build/board.o
	$(CC) -I ./library/SFML-2.5.1/include -c ./src/renderer.cpp -o ./build/renderer.o
	$(CC) -I ./library/SFML-2.5.1/include -c ./src/main.cpp -o ./build/main.o 

	$(CC) -L ./library/SFML-2.5.1/lib -o ./build/main.exe ./build/main.o ./build/renderer.o ./build/board.o -lsfml-graphics -lsfml-window -lsfml-system

fresh_build: clean_all copy_assets copy_dependencies build_app clean_object
	build/main.exe