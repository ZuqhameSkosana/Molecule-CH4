DOC = doc
JAVAC=javac
JFLAGS=-g

CLASSES=BarrierReusable.java \
	Carbon.java \
	Hydrogen.java \
	Methane.java \
	RunSimulation.java \
	
	

.SUFFIXES:.java .class .html

.java.class:
	$(JAVAC) $(JFLAG) $*.java

default:	classes
classes:	$(CLASSES:.java=.class)

doc:
	javadoc -d $(DOC) *.java

clean:
	$(RM) *.class

run:
	java RunSimulation 12 3
