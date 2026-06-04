import subprocess
import sys

def main():
    try:
        output = subprocess.check_output(['git', 'show', 'HEAD:modules/swagger-annotations/pom.xml'], stderr=subprocess.STDOUT)
        with open('original_pom.xml', 'wb') as f:
            f.write(output)
        print("Success")
    except subprocess.CalledProcessError as e:
        print("Error:", e.output.decode('utf-8'))

if __name__ == "__main__":
    main()
