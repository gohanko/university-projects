CC = g++

bd:
	$(CC) -I ./library/SFML-2.5.1/include -c ./src/main.cpp -o ./build/MoeChess.exe
	$(CC) -L ./library/SFML-2.5.1/lib -o ./build/MoeChess.exe  -lmingw32 -lsfml-main -lsfml-main -lsfml-graphics -lsfml-window -lsfml-system

clean:
ifeq ($(OS), Windows_NT)
	del /s MoeChess.exe
endif