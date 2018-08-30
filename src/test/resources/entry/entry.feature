
Feature: Entry
@entrada
	Scenario: Visitor check the entries
		Given the visitor
		When goes to entry section
		Then he sees the entry "Dummy entry"