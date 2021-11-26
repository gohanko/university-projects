all:
	gcc -I ./library/SFML-2.5.1/include -c ./src/main.cpp -o ./build/MoeChess.exe
	gcc -L ./library/SFML-2.5.1/lib -o ./build/MoeChess.exe  -lmingw32 -lsfml-main -lsfml-main -lsfml-graphics -lsfml-window -lsfml-system